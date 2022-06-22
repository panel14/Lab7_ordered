package itmo.data;

import itmo.exceptions.CollectionException;

import java.util.Date;

/**
 * Person class
 */
public class Person implements Comparable<Person> {
    /**
     * Name of person
     */
    private String name; //Поле не может быть null, Строка не может быть пустой
    /**
     * person's birthday
     */
    private Date birthday; //Поле может быть null
    /**
     * color of person's eyes
     */
    private Color eyeColor; //Поле может быть null
    /**
     * color of person's hair
     */
    private Color hairColor; //Поле не может быть null
    /**
     * person's nationality
     */
    private Country nationality; //Поле может быть null

    /**
     * @return eyeColor
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Default constructor
     *
     * @see Person#Person(String, Date, Color, Color, Country)
     */
    public Person() {
    }

    /**
     * @param name        name of person
     * @param birthday    date of birth
     * @param eyeColor    color of eyes
     * @param hairColor   color of hair
     * @param nationality country of birth
     * @see Person#Person()
     */
    public Person(String name, Date birthday, Color eyeColor, Color hairColor, Country nationality) throws CollectionException {
        this.setName(name);
        this.setBirthday(birthday);
        this.setEyeColor(eyeColor);
        this.setHairColor(hairColor);
        this.setNationality(nationality);
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name person's name
     */
    public void setName(String name) throws CollectionException {

        if (name == null || name.equals(""))
            throw new CollectionException("name must not be empty");

        this.name = name;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday person's birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @param eyeColor color of person's eyes
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * @return hairColor
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * @param hairColor color of person's hair
     */
    public void setHairColor(Color hairColor) throws CollectionException {

        if (hairColor == null)
            throw new CollectionException("hair color must not be null");

        this.hairColor = hairColor;
    }

    /**
     * @return nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * @param nationality person's nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * @return str
     */
    @Override
    // Overriding the `toString()` method of the Object class.
    public String toString() {
        String info = "";
        info += "\n-Name = " + name;
        info += "\n-Birthday = " + birthday;
        info += "\n-Nationality = " + nationality;
        info += "\n-EyeColor = " + eyeColor;
        info += "\n-HairColor = " + hairColor;

        return info;
    }

    /**
     * @param o other person
     * @return int
     */

    @Override
    public int compareTo(Person o) {
        if (o == null)
            return 1;

        return name.compareTo(o.getName());
    }
}
