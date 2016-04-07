package ac.uk.ncl.b3003143.csc2023.assignment2.testing;

import ac.uk.ncl.b3003143.csc2023.assignment2.main.RopeCoil;
import ac.uk.ncl.b3003143.csc2023.assignment2.main.RopeOrder;

/**
 * Test the functionality of the RopeCoil class.
 * Created by Natalie Sandford on 03/12/2015.
 */
public class RopeCoilTest {

    private final RopeCoil coil = new RopeCoil();
    private final RopeOrder order = new RopeOrder();

    public static void main(String[] args) {
        RopeCoilTest rct = new RopeCoilTest();

        /* Test the toString method to print out the coil variables. */
        System.out.println("Coil variables:");
        rct.test_toString();

        /* Cut the coil and print out the result. */
        System.out.println("\nCut the coil:");
        rct.cut_coil();

        /* Try to cut the rope too many times so that it tests the error checking for changing the length of a coil. */
        System.out.println("\nTry to cut the coil too many times:");
        rct.cant_cut_coil_too_many_times();
    }

    /**
     * Print out the coil variables.
     */
    public void test_toString() {
        System.out.println(coil.toString());
    }

    /**
     * Attempt to cut the coil with a rope order.
     */
    public void cut_coil() {
        System.out.println("Cut of " + order.getLength());
        try {
            coil.cutRopeCoil(order);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        test_toString();
    }

    /**
     * Force the coil to throw an exception after attempting to cut the coil too many times.
     */
    public void cant_cut_coil_too_many_times() {
        RopeOrder order = new RopeOrder();
        System.out.println("Cut of " + order.getLength());
        try {
            for (int i = 0; i <= 200; i++) {
                coil.cutRopeCoil(order);
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        test_toString();
    }
}
