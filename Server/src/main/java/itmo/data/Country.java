package itmo.data;

import itmo.exceptions.CollectionException;

import java.util.Arrays;
import java.util.Locale;

/**
 * enum for Country
 */
public enum Country {
    /**
     * RUSSIA
     */
    RUSSIA,
    /**
     * FRANCE
     */
    FRANCE,
    /**
     * CHINA
     */
    CHINA,
    /**
     * ITALY
     */
    ITALY,
    /**
     * NORTH_KOREA
     */
    NORTH_KOREA;

    /**
     * @param countryString string with a country
     * @return nationality
     */
    public static Country parse(String countryString) throws CollectionException {
        String formattedCountryString = countryString.trim().toUpperCase(Locale.ROOT);
        try {
            return Country.valueOf(formattedCountryString);
        } catch (IllegalArgumentException e) {
            throw new CollectionException("There is no constant (" + countryString + ") in " + Country.class.getSimpleName() + ": " + Arrays.toString(values()));
        }
    }
}
