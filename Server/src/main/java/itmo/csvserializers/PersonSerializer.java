package itmo.csvserializers;

import itmo.data.Person;

/**
 * class that helps to convert person to CSV
 */
public class PersonSerializer {
    /**
     * @param person    person to CSV
     * @param delimiter divides fields in CSV fields
     * @return CSV string
     */
    public String toCSV(Person person, char delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(person.getName()).append(delimiter);
        if (person.getBirthday() == null)
            stringBuilder.append(delimiter);
        else
            stringBuilder.append(person.getBirthday().toString()).append(delimiter);
        stringBuilder.append(person.getHairColor().toString()).append(delimiter);
        if (person.getEyeColor() == null)
            stringBuilder.append(delimiter);
        else
            stringBuilder.append(person.getEyeColor().toString()).append(delimiter);
        if (person.getNationality() == null)
            stringBuilder.append(delimiter);
        else
            stringBuilder.append(person.getNationality().toString()).append(delimiter);

        String s = person.getName() + delimiter + person.getBirthday().toString() +
                delimiter + person.getHairColor() + delimiter + person.getEyeColor() +
                delimiter + person.getNationality();
        return s;
    }
}
