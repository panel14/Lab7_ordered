package itmo.data.builders;

import itmo.data.Coordinates;
import itmo.io.Scannable;


/**
 * class that creates coordinates
 */
public class CoordinatesBuilder {
    private final Coordinates coordinates;
    private final boolean isConsole;


    public CoordinatesBuilder(boolean isConsole) {
        coordinates = new Coordinates();
        this.isConsole = isConsole;
    }

    /**
     * @param scannable scannable
     * @return coordinates
     */
    public Coordinates build(Scannable scannable) throws Exception {
        this.buildX(scannable);
        this.buildY(scannable);
        return coordinates;
    }

    /**
     * @param scannable scannable
     */
    private void buildX(Scannable scannable) throws Exception {

        if (isConsole) {
            System.out.println("Enter X: ");
            String x = scannable.readLine();
            try {
                coordinates.setX(Double.valueOf(x));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildX(scannable);
            }
            return;
        }
        String x = scannable.readLine();
        coordinates.setX(Double.valueOf(x));
    }

    /**
     * @param scannable scannable
     */
    private void buildY(Scannable scannable) throws Exception {

        if (isConsole) {
            System.out.println("Enter Y: ");
            String y = scannable.readLine();
            try {
                coordinates.setY(Float.valueOf(y));
            } catch (Exception e) {
                System.out.println("Oops: " + e.getMessage());
                buildY(scannable);
            }
            return;
        }
        String y = scannable.readLine();
        coordinates.setY(Float.valueOf(y));
    }
}
