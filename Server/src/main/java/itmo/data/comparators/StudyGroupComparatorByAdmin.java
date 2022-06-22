package itmo.data.comparators;

import itmo.data.Person;
import itmo.data.StudyGroup;

import java.util.Comparator;

/**
 * class that compares two StudyGroups by groupAdmins
 */
public class StudyGroupComparatorByAdmin implements Comparator<StudyGroup> {
    /**
     * @param o1 obj1
     * @param o2 obj2
     * @return int
     */
    @Override
    public int compare(StudyGroup o1, StudyGroup o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;
        if (o2 == null)
            return 1;

        Person p1 = o1.getGroupAdmin();
        Person p2 = o2.getGroupAdmin();
        PersonComparator personComparator = new PersonComparator();
        return personComparator.compare(p1, p2);
    }
}
