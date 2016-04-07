package ac.uk.ncl.b3003143.csc2023.assignment2.testing;

import ac.uk.ncl.b3003143.csc2023.assignment2.main.RopeOrder;

/**
 * Test the functionality of the RopeOrder class.
 * Created by Natalie Sandford on 03/12/2015.
 */
public class RopeOrderTest {

    RopeOrder order = new RopeOrder();

    public static void main(String[] args) {
        RopeOrderTest rct = new RopeOrderTest();

        /* Test the toString method to print out the order variables.. */
        System.out.println("Order Variables:");
        rct.print_variables();

    }

    public void print_variables() {
        System.out.println(order.toString());
    }
}
