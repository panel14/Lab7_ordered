package itmo.data;

import itmo.exceptions.CollectionException;

/**
 * coordinates
 */
public class Coordinates {
    private Double x; //Значение поля должно быть больше -107, Поле не может быть null
    private Float y; //Максимальное значение поля: 108, Поле не может быть null

    public Coordinates() {
    }

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinates(double x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the y coordinate of the point
     *
     * @return The value of the y field.
     */
    public float getY() {
        return y;
    }

    /**
     * It sets the y coordinate of the point.
     *
     * @param y The y coordinate of the point.
     */
    public void setY(Float y) throws CollectionException {

        if (y == null || y > 108)
            throw new CollectionException("y must be lower than 108");
        this.y = y;
    }

    /**
     * Get the x coordinate of the point
     *
     * @return The value of the instance variable x.
     */
    public double getX() {
        return x;
    }

    /**
     * It sets the x value to the given value.
     *
     * @param x The x coordinate of the point.
     */
    public void setX(Double x) throws CollectionException {

        if (x == null || x <= -107)
            throw new CollectionException("x must be greater than 107 and x must not be null");
        this.x = x;
    }


    @Override
    // Overriding the toString method of the Object class.
    public String toString() {
        return "\n-X = " + getX() + "\n-Y = " + getY();
    }


}

