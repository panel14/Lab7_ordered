package itmo.data.comparators;

import itmo.data.Person;

import java.util.Comparator;

/**
 * class that compares two Persons
 */
public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;

        return o1.compareTo(o2);
    }
}
