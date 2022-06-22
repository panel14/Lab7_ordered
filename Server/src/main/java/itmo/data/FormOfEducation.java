package itmo.data;

import itmo.exceptions.CollectionException;

import java.util.Arrays;
import java.util.Locale;

/**
 * enum for FormOfEducation
 */
public enum FormOfEducation {
    /**
     * DISTANCE_EDUCATION
     */
    DISTANCE_EDUCATION("DISTANCE_EDUCATION"),
    /**
     * FULL_TIME_EDUCATION
     */
    FULL_TIME_EDUCATION("FULL_TIME_EDUCATION"),
    /**
     * EVENING_CLASSES
     */
    EVENING_CLASSES("EVENING_CLASSES");

    private final String value;

    public String getValue() {
        return value;
    }

    FormOfEducation(String value) {
        this.value = value;
    }

    /**
     * @param formOfEducationString string with type of forms of education
     * @return formOfEducation
     */
    public static FormOfEducation parse(String formOfEducationString) throws CollectionException {
        String formattedFormOfEducationString = formOfEducationString.trim().toUpperCase(Locale.ROOT);
        try {
            return valueOf(formattedFormOfEducationString);
        } catch (IllegalArgumentException e) {
            throw new CollectionException("There is no constant (" + formOfEducationString + ") in " + FormOfEducation.class.getSimpleName() + ": " + Arrays.toString(values()));
        }
    }
}
