package itmo.data.comparators;

import itmo.data.StudyGroup;

import java.util.Comparator;

/**
 * class that compares two StudyGroups
 */
public class StudyGroupComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup o1, StudyGroup o2) {
        if (o1 == o2)
            return 0;
        if (o1 == null)
            return -1;

        return o1.compareTo(o2);
    }
}
