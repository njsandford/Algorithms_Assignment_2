package ac.uk.ncl.b3003143.csc2023.assignment2.main;

import java.util.Random;

/**
 * Created by Natalie Sandford on 02/12/2015.
 * A rope order which consists of an integer variable for the length of an order.
 * Can modify and return the length of an order.
 * Each rope order can only be of length 1 - 100.
 */
public class RopeOrder {

    /* Constant variables */
    private final static int MIN_LENGTH = 1;
    private final static int MAX_LENGTH = 100;
    private final static int RANGE = (MAX_LENGTH - MIN_LENGTH) + 1;

    /* Object fields */
    private int length;

    /**
     * Constructor, sets the length of the rope order to a random integer value
     *      in the range of 1 - 100.
     */
    public RopeOrder() {
        Random random = new Random();
        int length = random.nextInt(RANGE) + MIN_LENGTH;

        try {
            setLength(length);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy constructor, allows the variables of an instance of this class to be copied
     *      into the variables of another instance of this class.
     * @param toCopy the object to be coppied.
     */
    public RopeOrder(RopeOrder toCopy) {
        try {
            setLength(toCopy.getLength());
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overridden toString method which returns a string representing the rope order.
     * @return a string containing the length variable of the rope order.
     */
    @Override
    public String toString()
    {
        return ("Length: " + length);
    }

    /**
     * Set the length of the rope order.
     * @param length integer assigned to the length of the rope order.
     * @throws IllegalArgumentException
     */
    private void setLength(int length) throws IllegalArgumentException {
        if (length >= MIN_LENGTH && length <= MAX_LENGTH)
            this.length = length;
        else throw new IllegalArgumentException("The length of an order must be within the range 1 - 100");
    }

    /**
     * Get the length of the rope order.
     * @return integer length.
     */
    public int getLength() {
        return this.length;
    }
}
