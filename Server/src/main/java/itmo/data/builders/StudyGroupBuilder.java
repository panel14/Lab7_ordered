package itmo.data.builders;

import itmo.data.FormOfEducation;
import itmo.data.Person;
import itmo.data.StudyGroup;
import itmo.io.Scannable;
import itmo.utility.IdGenerator;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

/**
 * class that creates studyGroups
 */
public class StudyGroupBuilder {

    private final StudyGroup studyGroup;
    private final boolean isConsole;

    public StudyGroupBuilder(boolean isConsole) {
        studyGroup = new StudyGroup();
        this.isConsole = isConsole;
    }

    /**
     * @param scannable scannable
     * @return studyGroup
     */
    public StudyGroup build(Scannable scannable, String user) throws Exception {
        studyGroup.setId(IdGenerator.generateId());
        this.buildName(scannable);
        this.buildCoordinates(scannable);
        this.buildCreationDate(scannable);
        this.buildStudentsCount(scannable);
        this.buildExpelledStudents(scannable);
        this.buildShouldBeExpelled(scannable);
        this.buildFormOfEducation(scannable);
        this.buildGroupAdmin(scannable);
        studyGroup.setOwner(user);

        return studyGroup;
    }

    /**
     * @param scannable scannable
     */
    private void buildName(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the name: ");
            String name = scannable.readLine();
            try {
                studyGroup.setName(name);
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildName(scannable);
            }
            return;
        }
        String name = scannable.readLine();
        studyGroup.setName(name);
    }

    /**
     * @param scannable scannable
     */
    private void buildCoordinates(Scannable scannable) throws Exception {
        CoordinatesBuilder coordinatesBuilder = new CoordinatesBuilder(isConsole);
        studyGroup.setCoordinates(coordinatesBuilder.build(scannable));
    }

    /**
     * @param scannable scannable
     */
    private void buildCreationDate(Scannable scannable) {
        studyGroup.setCreationDate(LocalDateTime.now());
    }

    /**
     * @param scannable scannable
     */
    private void buildStudentsCount(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the studentsCount: ");
            String studentsCount = scannable.readLine();
            try {
                studyGroup.setStudentsCount(Long.valueOf(studentsCount));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildStudentsCount(scannable);
            }
            return;
        }
        String studentsCount = scannable.readLine();
        studyGroup.setStudentsCount(Long.valueOf(studentsCount));
    }

    /**
     * @param scannable scannable
     */
    private void buildExpelledStudents(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the expelledStudents: ");
            String expelledStudents = scannable.readLine();
            if (expelledStudents.equals("")) {
                studyGroup.setExpelledStudents(null);
                return;
            }
            try {
                studyGroup.setExpelledStudents(Integer.valueOf(expelledStudents));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildExpelledStudents(scannable);
            }
            return;
        }
        String expelledStudents = scannable.readLine();
        if (expelledStudents.equals("")) {
            studyGroup.setExpelledStudents(null);
            return;
        }
        studyGroup.setExpelledStudents(Integer.valueOf(expelledStudents));
    }

    /**
     * @param scannable scannable
     */
    private void buildShouldBeExpelled(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the shouldBeExpelledStudents: ");
            String shouldBeExpelledStudents = scannable.readLine();
            if (shouldBeExpelledStudents.equals("")) {
                studyGroup.setShouldBeExpelled(null);
                return;
            }
            try {
                studyGroup.setShouldBeExpelled(Long.valueOf(shouldBeExpelledStudents));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildShouldBeExpelled(scannable);
            }
            return;
        }
        String shouldBeExpelledStudents = scannable.readLine();
        if (shouldBeExpelledStudents.equals("")) {
            studyGroup.setShouldBeExpelled(null);
            return;
        }
        studyGroup.setShouldBeExpelled(Long.valueOf(shouldBeExpelledStudents));
    }

    /**
     * @param scannable scannable
     */
    private void buildFormOfEducation(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Enter the formOfEducation " + Arrays.toString(FormOfEducation.values()) + ": ");
            String formOfEducation = scannable.readLine();
            if (formOfEducation.equals("")) {
                studyGroup.setFormOfEducation(null);
                return;
            }
            try {
                studyGroup.setFormOfEducation(FormOfEducation.parse(formOfEducation));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildFormOfEducation(scannable);
            }
            return;
        }
        String formOfEducation = scannable.readLine();
        if (formOfEducation.equals("")) {
            studyGroup.setFormOfEducation(null);
            return;
        }
        studyGroup.setFormOfEducation(FormOfEducation.parse(formOfEducation));
    }

    /**
     * @param scannable scannable
     */
    private void buildGroupAdmin(Scannable scannable) throws Exception {
        if (isConsole) {
            System.out.println("Do you want to enter groupAdmin?");
            String ans = scannable.readLine();
            if (ans.trim().toUpperCase(Locale.ROOT).equals("YES")) {
                PersonBuilder personBuilder = new PersonBuilder(isConsole);
                Person person = personBuilder.build(scannable);
                studyGroup.setGroupAdmin(person);
            }
        } else {
            PersonBuilder personBuilder = new PersonBuilder(isConsole);
            try {
                Person person = personBuilder.build(scannable);
                studyGroup.setGroupAdmin(person);
            } catch (Exception e) {
                studyGroup.setGroupAdmin(null);
            }
        }
    }
}
