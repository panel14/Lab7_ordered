package itmo.csvserializers;

import itmo.data.Coordinates;

/**
 * class that helps to convert coordinates to CSV
 */
public class CoordinatesSerializer {

    /**
     * @param coordinates coordinates to CSV
     * @param delimiter   divides fields in CSV fields
     * @return CSV string
     */
    public String toCSV(Coordinates coordinates, char delimiter) {
        return String.valueOf(coordinates.getX()) + delimiter + coordinates.getY();
    }
}
