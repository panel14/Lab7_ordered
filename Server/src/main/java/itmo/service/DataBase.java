package itmo.service;

import itmo.collection.VectorCollection;
import itmo.data.*;
import itmo.io.FileScan;
import itmo.io.Scannable;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  class work with database
 */

public class DataBase {
    //Данные для соединения с бд
    private static final String url = "jdbc:postgresql://localhost:9999/studs";
    private static String login = "login";
    private static String password = "password";

    private static Connection connection;
    private static boolean isConnected = false;

    //SQL селекторы для запросов к бд
    private static final String IS_EXIST_REQUEST = "SELECT * FROM users WHERE login LIKE ?";
    private static final String IS_AUTH_REQUEST = "SELECT * FROM users WHERE login LIKE ? AND password LIKE ?";
    private static final String INSERT_USER_REQUEST = "INSERT INTO users (login, password) VALUES (?,?)";
    private static final String GET_COLLECTION = "SELECT * FROM groups";
    private static final String SAVE_COLLECTION = "INSERT INTO groups" +
            "(name," +
            "coord_x," +
            "coord_y,"  +
            "creation_date," +
            "student_count," +
            "expelled_students," +
            "should_be_expelled," +
            "form_education," +
            "person_name," +
            "person_date," +
            "person_eye_color," +
            "person_hair_color," +
            "person_country," +
            "owner)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //Чтение данных для подключения к бд (логин и пароль)
    /**
     * Method for reading connection data (login and password)
     * @throws Exception
     */
    private static void readData() throws Exception {
        Scannable reader = new FileScan("data.secret");
        String data = reader.readLine();
        login = data.split(";")[0];
        password = data.split(";")[1];
        reader.close();
    }

    //Подключение к базе данных
    /**
     * Method establishes a connection to the database
     */
    private static void connectDB() {
        try {
            readData();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
            isConnected = true;
        }
        catch (Exception e) {
            System.out.println("Connection fault: " + e.getMessage());
            isConnected = false;
        }
    }

    //Проверка, существует ли пользователь
    //Отправляем запрос к таблице users - если найден пользователь с данным логином - true, иначе false
    /**
     * check is user exist in database
     * @param user is a user's data
     * @return bool as result - does user exist
     */
    public static boolean isExist(User user) {
        try {
            PreparedStatement checkStatement = connection.prepareStatement(IS_EXIST_REQUEST);
            checkStatement.setString(1, user.login);
            ResultSet resultSet = checkStatement.executeQuery();
            return (resultSet.next());
        } catch (SQLException e) {
            return false;
        }
    }

    //Регистрация нового пользователя
    //Проверяем соединение и существует ли данный пользователь
    //Если соединение есть, и пользователя с таким логином нет в базе - добавляем его в базу
    /**
     * registers a user
     * @param user is a user's data
     * @return DBResponse object - boolean result of registration and String comment
     */
    public static DBResponse register(User user) {
        if (!isConnected) connectDB();
        if (!isConnected) return new DBResponse(false, "Connection fault.");
        if (isExist(user)) return new DBResponse(false, "User with this login already exists.");
        try {
            String hashPassword = HashCoder.encodeSHA224(user.password);
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_USER_REQUEST);
            insertStatement.setString(1, user.login);
            insertStatement.setString(2, hashPassword);
            insertStatement.executeUpdate();
            insertStatement.close();
            return new DBResponse(true, "User successfully registered.");
        } catch (SQLException e) {
            e.printStackTrace();
            return new DBResponse(false, "Failed to get user data.: " + e.getMessage());
        }
    }

    //Проверяем, аутентифицирован ли пользователь
    //Если такой логин есть в базе и пароли совпадают - пользователь прошёл аутентификацию
    /**
     * checks user authentication
     * @param user user is a user's data
     * @return DBResponse object - boolean result of authentication and String comment
     */
    public static DBResponse isAuth(User user) {
        if (!isConnected) connectDB();
        if (!isConnected) return new DBResponse(false, "Connection fault.");
        if (!isExist(user)) return new DBResponse(false, "There is no user with this login.");
        try {
            String hashPassword = HashCoder.encodeSHA224(user.password);

            PreparedStatement checkPasswordState = connection.prepareStatement(IS_AUTH_REQUEST);
            checkPasswordState.setString(1, user.login);
            checkPasswordState.setString(2, hashPassword);

            ResultSet checkSet = checkPasswordState.executeQuery();
            if (checkSet.next())
                return new DBResponse(true, "User verified.");
            else return new DBResponse(false, "Wrong password.");
        } catch (SQLException e) {
            e.printStackTrace();
            return new DBResponse(false, "Failed to get user data.: " + e.getMessage());
        }
    }

    //Получаем коллекцию из базы данных с помощью SQL-запроса
    //Из ResultSet построчно считываем данные в группу, группу добавляем в общую коллекцию
    /**
     * get collection from database
     * @return collection
     */
    public static VectorCollection<StudyGroup> getCollection() {
        if (!isConnected) connectDB();
        VectorCollection<StudyGroup> groups = new VectorCollection<>();
        try {
            ResultSet collection = connection.prepareStatement(GET_COLLECTION).executeQuery();

            while (collection.next()){
                StudyGroup group = new StudyGroup();
                group.setId(collection.getInt(1));
                group.setName(collection.getString(2));

                Coordinates coordinates = new Coordinates();
                coordinates.setX(collection.getDouble(3));
                coordinates.setY(collection.getFloat(4));
                group.setCoordinates(coordinates);

                LocalDateTime time = collection.getTimestamp(5).toLocalDateTime();
                group.setCreationDate(time);

                group.setStudentsCount(collection.getLong(6));
                group.setExpelledStudents(collection.getInt(7));

                group.setShouldBeExpelled(collection.getLong(8));

                FormOfEducation form = (collection.getString(9) == null) ? null :
                        FormOfEducation.parse(collection.getString(9));
                group.setFormOfEducation(form);

                Person admin = null;
                String name = collection.getString(10);
                if (!(name == null)){
                    admin = new Person();
                    admin.setName((collection.getString(10) == null) ? null : collection.getString(10));

                    Date personDate = (collection.getDate(11) == null) ? null : collection.getDate(11);
                    admin.setBirthday(personDate);

                    Color eyeColor = (collection.getString(12) == null) ? null :
                            Color.valueOf(collection.getString(12));
                    admin.setEyeColor(eyeColor);

                    admin.setHairColor(Color.valueOf(collection.getString(13)));
                    admin.setNationality(Country.parse(collection.getString(14)));
                }

                group.setGroupAdmin(admin);
                group.setOwner(collection.getString(15));

                groups.add(group);
            }

        } catch (Exception e) {
            System.out.println("Не удалось обратиться к коллекции: " + e.getMessage());
        }
        return groups;
    }

    //Сохраняем группу аналогично - собираем PreparedStatement из группы и отправляем на запись в бд
    /**
     * save group
     * @param studyGroup is group that need to be saved
     */
    public static void saveGroup(StudyGroup studyGroup) {
        if (!isConnected) connectDB();
        try {
            PreparedStatement saveState = connection.prepareStatement(SAVE_COLLECTION);
            saveState.setString(1, studyGroup.getName());
            saveState.setDouble(2, studyGroup.getCoordinates().getX());
            saveState.setDouble(3, studyGroup.getCoordinates().getY());

            Timestamp date = Timestamp.valueOf(studyGroup.getCreationDate());
            saveState.setTimestamp(4, date);

            saveState.setLong(5, studyGroup.getStudentsCount());
            saveState.setInt(6, studyGroup.getExpelledStudents());
            saveState.setDouble(7, studyGroup.getShouldBeExpelled());
            saveState.setString(8, studyGroup.getFormOfEducation() == null ? null :
                    studyGroup.getFormOfEducation().getValue());

            Person admin = studyGroup.getGroupAdmin();
            if (admin != null) {
                saveState.setString(9, admin.getName());
                saveState.setDate(10, (admin.getBirthday() == null) ? null :
                        new java.sql.Date(admin.getBirthday().getTime()));
                saveState.setString(11, (admin.getEyeColor() == null) ? null : admin.getEyeColor().toString());
                saveState.setString(12, admin.getHairColor().toString());
                saveState.setString(13, admin.getNationality().toString());
            }
            else {
                saveState.setString(9, null);
                saveState.setDate(10, null);
                saveState.setString(11, null);
                saveState.setString(12, null);
                saveState.setString(13, null);
            }
            saveState.setString(14, studyGroup.getOwner());
            saveState.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Saving fault: " + e.getMessage());
        }
    }

    //Поочереди с помощью вышеописанного метода сохраняем всю коллекцию
    /**
     * save all collection of groups
     * @param collection is collection that need to be saved
     */
    public static void saveCollection(VectorCollection<StudyGroup> collection) {
        try {
            connection.createStatement().executeUpdate("TRUNCATE groups");
            for (StudyGroup group : collection)
                saveGroup(group);
        } catch (SQLException e) {
            System.out.println("Saving fault: " + e.getMessage());
        }
    }


    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection closing fault.");
        }
    }

}
