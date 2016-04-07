package ac.uk.ncl.b3003143.csc2023.assignment2.main;

import java.util.Random;

/**
 * Created by Natalie Sandford on 01/12/2015.
 * A rope coil which consists of start length and remaining length integer
 *      variables, and an isTooShort boolean variable.
 * Can update the remaining length of each coil.
 * Each rope coil can only have a starting length between 100 - 200.
 */
public class RopeCoil {

    /* Constant variables */
    private final static int MIN_LENGTH = 100;
    private final static int MAX_LENGTH = 200;
    private final static int RANGE = (MAX_LENGTH - MIN_LENGTH) + 1;
    private final static int SCRAP_LENGTH = 5;

    /* Object fields */
    private int startLength;
    private int remainingLength;
    private boolean isTooShort = false;

    /**
     * Constructor, sets the length of the rope coil to a random integer value
     *      in the range of 100 - 200 and sets remaining length to this value also.
     */
    public RopeCoil() {
        Random random = new Random();
        int length = random.nextInt(RANGE) + MIN_LENGTH;

        try {
            setStartLength(length);
            setRemainingLength(startLength);
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
    public RopeCoil(RopeCoil toCopy) {
        try {
            setStartLength(toCopy.getStartLength());
            setRemainingLength(toCopy.getStartLength());
            setIsTooShort(toCopy.isTooShort);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overridden toString method which returns a string representing the rope coil.
     * @return a string containing the start length, remaining length variables, and
     *      the boolean value for if the length of the rope coil is less than 5.
     */
    @Override
    public String toString() {
        return ("Start Length: " + startLength + ", Remaining Length: " + remainingLength + ", isTooShort: " + isTooShort());
    }

    /**
     * Set the start length of the rope coil.
     * @param startLength the length that is assigned to the startLength field.
     * @throws IllegalArgumentException if the length is not within the correct range.
     */
    private void setStartLength(int startLength) throws IllegalArgumentException {
        if (startLength >= MIN_LENGTH && startLength <= MAX_LENGTH)
            this.startLength = startLength;
        else throw new IllegalArgumentException("The start length of a coil must be between 100 and 200.");
    }

    /**
     * Get the starting length of the coil of rope.
     * @return integer startLength.
     */
    public int getStartLength() { return this.startLength; }

    /**
     * Set the remaining length of the rope coil after making a cut.
     * @param remainingLength the length to be assigned to the remainingLength field.
     * @throws IllegalArgumentException
     */
    private void setRemainingLength(int remainingLength) throws IllegalArgumentException {
        if (remainingLength >= 0 && remainingLength <= startLength) {
            this.remainingLength = remainingLength;
            if (remainingLength < SCRAP_LENGTH)
                setIsTooShort(true);
        }
        else throw new IllegalArgumentException("Remaining length must be between 0 and the start length of the rope coil.");
    }

    /**
     * Set the boolean value for whether or not the rope coil is too short to be used again.
     * @param bool the boolean value to be assigned to the isTooShort field.
     */
    private void setIsTooShort(boolean bool) {
        this.isTooShort = bool;
    }

    /**
     * Get the boolean value for whether or not the rope coil is too short to be used again.
     * @return boolean isTooShort.
     */
    public boolean isTooShort() {
        return this.isTooShort;
    }

    /**
     * Get the remaining length of the rope coil.
     * @return integer remainingLength.
     */
    public int getRemainingLength() { return this.remainingLength; }

    /**
     * Cut the rope coil for a given rope order and update the remaining length of the coil.
     * @param order the rope order to cut from the rope coil.
     * @throws IllegalArgumentException if the length of the rope order does not exceed the
     *      remaining length of the rope coil.
     */
    public void cutRopeCoil(RopeOrder order) throws IllegalArgumentException {
        int newLength = getRemainingLength() - order.getLength();
        if (newLength >= 0) {
            try {
                setRemainingLength(newLength);
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("Rope to be cut must not exceed remaining length of rope coil.");
    }
}
