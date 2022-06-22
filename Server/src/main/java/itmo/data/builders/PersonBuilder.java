package itmo.data.builders;

import itmo.data.Color;
import itmo.data.Country;
import itmo.data.Person;
import itmo.io.Scannable;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * class that creates people
 */
public class PersonBuilder {
    private final Person person;
    private final boolean isConsole;

    public PersonBuilder(boolean isConsole) {
        person = new Person();
        this.isConsole = isConsole;
    }

    /**
     * @param scannable scannable
     * @return person
     */
    public Person build(Scannable scannable) throws Exception {
        this.buildName(scannable);
        this.buildBirthday(scannable);
        this.buildEyeColor(scannable);
        this.buildHairColor(scannable);
        this.buildNationality(scannable);
        return person;
    }

    /**
     * @param scannable scannable
     */
    private void buildName(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter person's name: ");
            String name = scannable.readLine();
            try {
                person.setName(name);
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildName(scannable);
            }
            return;
        }
        String name = scannable.readLine();
        if (name.equals(""))
            throw new Exception();
        person.setName(name);
    }

    /**
     * @param scannable scannable
     */
    private void buildBirthday(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the birthday (yyyy-MM-dd): ");
            String birthdayString = scannable.readLine();
            if (birthdayString.equals("")) {
                person.setBirthday(null);
                return;
            }
            try {
                person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayString));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildBirthday(scannable);
            }
            return;
        }
        String birthdayString = scannable.readLine();
        if (birthdayString.equals("")) {
            person.setBirthday(null);
            return;
        }
        person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayString));
    }

    /**
     * @param scannable scannable
     */
    private void buildEyeColor(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the eyeColor" + Arrays.toString(Color.values()) + ": ");
            String eyeColor = scannable.readLine();
            if (eyeColor.equals("")) {
                person.setEyeColor(null);
                return;
            }
            try {
                person.setEyeColor(Color.parse(eyeColor));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildEyeColor(scannable);
            }
            return;
        }
        String eyeColor = scannable.readLine();
        if (eyeColor.equals("")) {
            person.setEyeColor(null);
            return;
        }
        person.setEyeColor(Color.parse(eyeColor));
    }

    /**
     * @param scannable scannable
     */
    private void buildHairColor(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the hairColor " + Arrays.toString(Color.values()) + ": ");
            String hairColor = scannable.readLine();
            try {
                person.setHairColor(Color.parse(hairColor));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildHairColor(scannable);
            }
            return;
        }
        String hairColor = scannable.readLine();
        person.setHairColor(Color.parse(hairColor));
    }

    /**
     * @param scannable scannable
     */
    private void buildNationality(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the nationality " + Arrays.toString(Country.values()) + ": ");
            String nationality = scannable.readLine();
            if (nationality.equals("")) {
                person.setNationality(null);
                return;
            }
            try {
                person.setNationality(Country.parse(nationality));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildNationality(scannable);
            }
            return;
        }
        String nationality = scannable.readLine();
        if (nationality.equals("")) {
            person.setNationality(null);
            return;
        }
        person.setNationality(Country.parse(nationality));
    }
}
