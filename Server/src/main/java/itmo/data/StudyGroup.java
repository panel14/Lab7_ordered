package itmo.data;

import itmo.exceptions.CollectionException;
import itmo.interfaces.ShouldBeExpellable;

import java.time.LocalDateTime;

/**
 * class StudyGroup
 */
public class StudyGroup implements ShouldBeExpellable, Comparable<StudyGroup> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0

    private Integer expelledStudents; //Значение поля должно быть больше 0, Поле может быть null
    private Long shouldBeExpelled; //Значение поля должно быть больше 0, Поле может быть null
    private FormOfEducation formOfEducation; //Поле может быть null
    private Person groupAdmin; //Поле может быть null
    private String owner;

    public StudyGroup() {
    }

    /**
     * @param id               id
     * @param name             person's name
     * @param coordinates      StudyGroup's coordinates
     * @param creationDate     date of creation
     * @param studentsCount    number of students
     * @param expelledStudents number of expelled students
     * @param shouldBeExpelled number of students that should be expelled
     * @param formOfEducation  form of education
     * @param groupAdmin       StudyGroup's Admin
     */
    public StudyGroup(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, Long studentsCount, Integer expelledStudents, Long shouldBeExpelled, FormOfEducation formOfEducation, Person groupAdmin) throws CollectionException {
        this.setId(id);
        this.setName(name);
        this.setStudentsCount(studentsCount);
        this.setExpelledStudents(expelledStudents);
        this.setShouldBeExpelled(shouldBeExpelled);
        this.setCoordinates(coordinates);
        this.setFormOfEducation(formOfEducation);
        this.setCreationDate(creationDate);
        this.setGroupAdmin(groupAdmin);
    }

    /**
     * @return id
     */
    // A getter method for the field `id`.
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws CollectionException {
        if (id == null)
            throw new CollectionException("Id must not be null");
        if (id < 0)
            throw new CollectionException("Id must be greater than 0");

        this.id = id;
    }

    /**
     * @return name
     */
    // A getter method for the field `name`.
    public String getName() {
        return name;
    }

    /**
     * @param name person's name
     */
    public void setName(String name) throws CollectionException {
        if (name == null || name.equals(""))
            throw new CollectionException("name must be not empty");

        this.name = name;
    }

    /**
     * @param coordinates StudyGroup's coordinates
     */
    public void setCoordinates(Coordinates coordinates) throws CollectionException {
        if (coordinates == null)
            throw new CollectionException("coordinates must not be null");

        this.coordinates = coordinates;
    }

    /**
     * @param studentsCount number of students
     */
    public void setStudentsCount(Long studentsCount) throws CollectionException {

        if (studentsCount == null || studentsCount <= 0)
            throw new CollectionException("studentsCount must be greater than 0");

        this.studentsCount = studentsCount;
    }

    /**
     * @param expelledStudents number of expelled students
     */
    public void setExpelledStudents(Integer expelledStudents) throws CollectionException {

        if (expelledStudents != null && expelledStudents <= 0)
            throw new CollectionException("expelledStudents must be greater than 0");

        this.expelledStudents = expelledStudents;
    }

    /**
     * @param shouldBeExpelled number of students that should be expelled
     */
    public void setShouldBeExpelled(Long shouldBeExpelled) throws CollectionException {

        if (shouldBeExpelled != null && shouldBeExpelled <= 0)
            throw new CollectionException("shouldBeExpelledStudents must be greater than 0");

        this.shouldBeExpelled = shouldBeExpelled;
    }

    /**
     * @param formOfEducation form of education
     */
    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    /**
     * @param groupAdmin StudyGroup's Admin
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * @return coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return creationDate
     */
    // A getter method for the field `creationDate`.
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return studentsCount
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * @return expelledStudents
     */
    public Integer getExpelledStudents() {
        return expelledStudents;
    }

    /**
     * @return shouldBeExpelled
     */
    @Override
    public Long getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    /**
     * @return formOfEducation
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * @param creationDate date of creation
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return str
     */
    @Override
    public String toString() {
        String info = "";
        info += "StudyGroup №" + id;
        info += "\nName: " + name;
        info += "\nCoordinate: " + coordinates;
        info += " (created " + creationDate.toString() + ")";
        info += "\nStudentsCount: " + studentsCount;
        info += "\nShouldBeExpelled: " + shouldBeExpelled;
        info += "\nExpelledStudents: " + expelledStudents;
        info += "\nFormOfEducation: " + formOfEducation;
        info += "\nPerson: " + groupAdmin;
        info += "\nOwner: " + owner;
        return info;
    }

    /**
     * @param o other person
     * @return int
     */
    @Override
    public int compareTo(StudyGroup o) {
        if (o == null)
            return 1;

        return name.compareTo(o.getName());
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

