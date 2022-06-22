package itmo.data;

import itmo.exceptions.CollectionException;

import java.util.Arrays;
import java.util.Locale;

/**
 * enum for Color
 */
public enum Color {
    /**
     * GREEN
     */
    GREEN,
    /**
     * RED
     */
    RED,
    /**
     * BLUE
     */
    BLUE,
    /**
     * YELLOW
     */
    YELLOW,
    /**
     * BROWN
     */
    BROWN;

    /**
     * @param colorString string with a color
     * @return eyeColor, hairColor
     */
    public static Color parse(String colorString) throws CollectionException {
        String formattedColorString = colorString.trim().toUpperCase(Locale.ROOT);
        try {
            return valueOf(formattedColorString);
        } catch (IllegalArgumentException e) {
            throw new CollectionException("There is no constant (" + colorString + ") in " + Color.class.getSimpleName() + ": " + Arrays.toString(values()));
        }

    }
}
