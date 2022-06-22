package itmo.csvserializers;

import itmo.collection.VectorCollection;
import itmo.data.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * class that helps to convert vector to CSV
 */
public class VectorCollectionSerializer {

    /**
     * @param studyGroups vector to CSV
     * @param delimiter   divides fields in CSV fields
     * @return CSV string
     */
    public String toCSV(VectorCollection<StudyGroup> studyGroups, char delimiter) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(studyGroups.getCreationDate()).append("\n");

        studyGroups.forEach(studyGroup -> {
            stringBuilder.append(studyGroup.getId()).append(delimiter);
            stringBuilder.append(studyGroup.getName()).append(delimiter);
            stringBuilder.append(new CoordinatesSerializer().toCSV(studyGroup.getCoordinates(), delimiter)).append(delimiter);
            stringBuilder.append(studyGroup.getCreationDate()).append(delimiter);
            stringBuilder.append(studyGroup.getStudentsCount()).append(delimiter);
            if (studyGroup.getExpelledStudents() == null)
                stringBuilder.append(delimiter);
            else
                stringBuilder.append(studyGroup.getExpelledStudents()).append(delimiter);
            if (studyGroup.getShouldBeExpelled() == null)
                stringBuilder.append(delimiter);
            else
                stringBuilder.append(studyGroup.getShouldBeExpelled()).append(delimiter);
            if (studyGroup.getFormOfEducation() == null)
                stringBuilder.append(delimiter);
            else
                stringBuilder.append(studyGroup.getFormOfEducation()).append(delimiter);
            if (studyGroup.getGroupAdmin() == null)
                stringBuilder.append(delimiter).append("\n");
            else
                stringBuilder.append(new PersonSerializer().toCSV(studyGroup.getGroupAdmin(), delimiter)).append("\n");
        });

        return stringBuilder.toString();
    }

    /**
     * @param csvString CSV string
     * @param delimiter divides CSV fields in fields
     * @return collection
     */
    public VectorCollection<StudyGroup> toVectorCollection(String csvString, char delimiter) {

        VectorCollection<StudyGroup> vectorCollection = new VectorCollection<>();

        List<String> elements = new ArrayList<>(Arrays.asList(csvString.split("\n")));

        vectorCollection.setCreationDate(LocalDateTime.parse(elements.get(0)));

        elements.remove(0);

        elements.forEach(element -> {
            List<String> fields = new ArrayList<>(Arrays.asList(element.split(String.valueOf(delimiter))));

            try {
                StudyGroup studyGroup = new StudyGroup();
                studyGroup.setId(Integer.valueOf(fields.get(0)));
                studyGroup.setName(fields.get(1));

                Coordinates coordinates = new Coordinates();
                coordinates.setX(Double.valueOf(fields.get(2)));
                coordinates.setY(Float.valueOf(fields.get(3)));

                studyGroup.setCoordinates(coordinates);
                studyGroup.setCreationDate(LocalDateTime.parse(fields.get(4)));
                studyGroup.setStudentsCount(Long.valueOf(fields.get(5)));
                if (fields.get(6).equals(""))
                    studyGroup.setExpelledStudents(null);
                else
                    studyGroup.setExpelledStudents(Integer.valueOf(fields.get(6)));
                if (fields.get(7).equals(""))
                    studyGroup.setShouldBeExpelled(null);
                else
                    studyGroup.setShouldBeExpelled(Long.valueOf(fields.get(7)));
                if (fields.get(8).equals(""))
                    studyGroup.setFormOfEducation(null);
                else
                    studyGroup.setFormOfEducation(FormOfEducation.valueOf(fields.get(8)));

                Person person = new Person();

                if (fields.size() <= 9 || fields.get(9).equals("")) {
                    studyGroup.setGroupAdmin(null);
                } else {
                    person.setName(fields.get(9));
                    if (fields.get(10).equals(""))
                        person.setBirthday(null);
                    else
                        person.setBirthday(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ROOT).parse(fields.get(10)));
                    person.setHairColor(Color.valueOf(fields.get(11)));
                    if (fields.get(12).equals(""))
                        person.setEyeColor(null);
                    else
                        person.setEyeColor(Color.valueOf(fields.get(12)));
                    if (fields.get(13).equals(""))
                        person.setNationality(null);
                    else
                        person.setNationality(Country.valueOf(fields.get(13)));

                    studyGroup.setGroupAdmin(person);
                }

                vectorCollection.add(studyGroup);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        return vectorCollection;
    }
}
