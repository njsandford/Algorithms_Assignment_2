package ac.uk.ncl.b3003143.csc2023.assignment2.testing;

import ac.uk.ncl.b3003143.csc2023.assignment2.main.RopeCoil;
import ac.uk.ncl.b3003143.csc2023.assignment2.main.RopeCuttingProblem;

import java.util.List;

/**
 * Created by Natalie Sandford on 03/12/2015.
 * Test class for the RopeCuttingProblem class.
 */
public class RopeCuttingProblemTest {

    public static void main(String[] args) {
        RopeCuttingProblemTest rcpt = new RopeCuttingProblemTest();

        /* Test First Fit and Best Fit individually with 10 coils. */
        RopeCuttingProblem rcp = new RopeCuttingProblem(10);
        rcpt.firstFitTest(rcp);
        rcpt.bestFitTest(rcp);

        /* Test both algorithms using the same orders. */
        System.out.println("\n\nPerformance Results:");
        rcpt.compareFirstFitBestFit(10000);
        rcpt.compareFirstFitBestFit(25000);
        rcpt.compareFirstFitBestFit(50000);
        rcpt.compareFirstFitBestFit(75000);
        rcpt.compareFirstFitBestFit(100000);
        rcpt.compareFirstFitBestFit(250000);
        rcpt.compareFirstFitBestFit(500000);
        rcpt.compareFirstFitBestFit(750000);
        rcpt.compareFirstFitBestFit(1000000);
        rcpt.compareFirstFitBestFit(1250000);
    }

    /**
     * Tests that the first fit method using a small number of orders and that it produces the expected output.
     * Should print out only the coils of rope that are greater than 4m in length.
     * Should have allocated orders to the first coil long enough to make the cut from.
     * @param rcp the instance of the RopeCuttingProblem to use.
     */
    public void firstFitTest(RopeCuttingProblem rcp) {
        System.out.println("\nFIRST FIT:\n");
        rcp.firstFit();
        List<RopeCoil> FFCoils = rcp.getFirstFitCoilStock();
        System.out.println("All Orders:");
        rcp.displayAllOrders();
        System.out.println("\nCurrent Stock:");
        rcp.displayCurrentStock(FFCoils);
        System.out.println("\nTotal Ordered Coil Length: " + rcp.getTotalOrderedRopeCoilLength(FFCoils));
        System.out.println("Total Ordered Coil Quantity: " + rcp.getFFCoilCounter());
        System.out.println("Total Remaining Coil Length: " + rcp.getTotalRemainingRopeCoilLength(FFCoils));
        System.out.println("Total Rope Orders Length: " + (rcp.getTotalOrderedRopeCoilLength(FFCoils) - rcp.getTotalRemainingRopeCoilLength(FFCoils)));
    }

    /**
     * Tests that the best fit method using a small number of orders and that it produces the expected output.
     * Should print out only the coils of rope that are greater than 4m in length.
     * Should have allocated orders to the coil with the least remaining length that is long enough to make the cut from.
     * @param rcp the instance of the RopeCuttingProblem to use.
     */
    public void bestFitTest(RopeCuttingProblem rcp) {
        System.out.println("\nBEST FIT:\n");
        rcp.bestFit();
        List<RopeCoil> BFCoils = rcp.getBestFitCoilStock();
        System.out.println("All Orders:");
        rcp.displayAllOrders();
        System.out.println("\nCurrent Stock:");
        rcp.displayCurrentStock(BFCoils);
        System.out.println("\nTotal Ordered Coil Length: " + rcp.getTotalOrderedRopeCoilLength(BFCoils));
        System.out.println("Total Ordered Coil Quantity: " + rcp.getBFCoilCounter());
        System.out.println("Total Remaining Coil Length: " + rcp.getTotalRemainingRopeCoilLength(BFCoils));
        System.out.println("Total Rope Orders Length: " + (rcp.getTotalOrderedRopeCoilLength(BFCoils) - rcp.getTotalRemainingRopeCoilLength(BFCoils)));
    }

    /**
     * Compares the performance of the two algorithms using the same set of orders and coils.
     * The output of this test can be used to examine and compare the two algorithms side by side.
     * @param orderQuantity the number of orders to test the program with.
     */
    public void compareFirstFitBestFit(int orderQuantity){
        RopeCuttingProblem rcp = new RopeCuttingProblem(orderQuantity);

        System.out.println("\nPerformance with " + orderQuantity + " orders:\n");

        /**
         * Measure and print the total CPU time taken for each algorithm.
         */
        long FFStart = System.nanoTime();
        rcp.firstFit();
        long FFFinish = System.nanoTime();
        long FFTime = FFFinish - FFStart;
        System.out.printf("First Fit took %,d ns%n", FFTime);

        long BFStart = System.nanoTime();
        rcp.bestFit();
        long BFFinish = System.nanoTime();
        long BFTime = BFFinish - BFStart;
        System.out.printf("Best Fit took %,d ns%n", BFTime);

        /**
         * Print the total number of coils used by each algorithm.
         */
        int totalCoilsFF = rcp.getFFCoilCounter();
        System.out.println("Total coils used by First Fit: " + totalCoilsFF);

        int totalCoilsBF = rcp.getBFCoilCounter();
        System.out.println("Total coils used by Best Fit: " + totalCoilsBF);

        // Below line only used for outputting data to be easily copied into an excel document.
//        System.out.println(FFTime + "\t" + BFTime + "\t" + totalCoilsFF + "\t" + totalCoilsBF);
    }
}
