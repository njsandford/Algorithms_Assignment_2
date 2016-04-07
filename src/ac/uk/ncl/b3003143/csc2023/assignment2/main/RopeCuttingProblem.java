package ac.uk.ncl.b3003143.csc2023.assignment2.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natalie Sandford on 03/12/2015.
 * To solve the Rope Cutting Problem using two algorithms:
        * FFRCP - First Fit Rope Cutting Problem
        * BFRCP - Best Fit Rope Cutting Problem
 */
public class RopeCuttingProblem {

    /* Constant variable */
    private final static int INITIAL_BEST_FIT_LENGTH = 201;

    /* Unchangeable variables */
    private final List<RopeOrder> allOrders;
    private final List<RopeCoil> coilBank;

    /* Class fields */
    private List<RopeCoil> firstFitCoilStock;
    private List<RopeCoil> bestFitCoilStock;
    private int FFCoilCounter;
    private int BFCoilCounter;

    /**
     * Constructor, initialises the lists of current stock and orders as empty.
     */
    public RopeCuttingProblem(int orderQuantity) {
        this.allOrders = generateRopeOrders(orderQuantity);
        this.coilBank = generateRopeCoils(orderQuantity);
    }

    /**
     * The First Fit method, which uses the first rope coil that the current rope
     *      order can be cut from.
     * If a rope coil has a length of less than 5 it is considered too small to
     *      use and is removed from the list of coils in stock.
     */
    public void firstFit() {
        firstFitCoilStock = new ArrayList<>();
        FFCoilCounter = 0;
        boolean ropeFits;
        int currentCoilLength;
        int currentOrderLength;
        int bankIndex = 0; // Initialise index of the coil bank to 0 at the start of the method.

        // For all rope orders, initialise the current order length and the rope fits boolean.
        for (RopeOrder currentOrder : allOrders) {
            currentOrderLength = currentOrder.getLength();
            ropeFits = false;
            try {
                // While a coil to make the cut from has not been found, iterate through all coils to find one.
                while (ropeFits == false) {

                    // If the current stock of coils is not empty, then  iterate through the current stock.
                    if (!firstFitCoilStock.isEmpty()) {
                        for (RopeCoil currentCoil : firstFitCoilStock) {
                            currentCoilLength = currentCoil.getRemainingLength();

                            /* If the current order is equal to or shorter than the current coil, cut the order
                             * from that coil. */
                            if (currentOrderLength <= currentCoilLength) {
                                ropeFits = true;
                                currentCoil.cutRopeCoil(currentOrder);

                                // If the current coil is now too short, remove it from the current stock.
                                if (currentCoil.isTooShort()) {
                                    firstFitCoilStock.remove(currentCoil);
                                }
                                break; // Exit the while loop as the order has been cut.
                            }
                        }
                    }
                    break; // Exit the while loop as there are no current coils in stock.
                }
                /* If the order cant be cut from any of the current coils in stock, make a new coil
                 * cut the order from the new coil. */
                if (ropeFits == false) {

                    // Take the next coil from the bank and increment the value of the bank index.
                    RopeCoil newCoil = new RopeCoil(coilBank.get(bankIndex++));
                    firstFitCoilStock.add(newCoil);
                    newCoil.cutRopeCoil(currentOrder);
                    FFCoilCounter += 1; // Increment the coil counter for the First Fit algorithm.

                    // If the new coil is now too short, remove it from the current stock.
                    if (newCoil.isTooShort()) {
                        firstFitCoilStock.remove(newCoil);
                    }
                }
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The Best Fit method, which uses the rope coil with the least remaining
     *      length that each rope order can be cut from.
     * If a rope coil has a length of less than 5 it is considered too small to
     *      use and is removed from the list of coils in stock.
     */
    public void bestFit() {
        bestFitCoilStock = new ArrayList<>();
        BFCoilCounter = 0;
        boolean ropeFits;
        int currentOrderLength;
        int currentCoilLength;
        int bestFitLength;
        int bestFitIndex;
        int bankIndex = 0; // Initialise index of the coil bank to 0 at the start of the method.
        RopeCoil bestFit;

        /* For all rope orders, initialise the current order length, rope fits boolean, initial
         * best fit length, and the bestFitIndex. */
        for (RopeOrder currentOrder : allOrders) {
            currentOrderLength = currentOrder.getLength();
            ropeFits = false;
            bestFitLength = INITIAL_BEST_FIT_LENGTH;
            bestFitIndex = -1;

            /* If the current stock of coils is not empty, then  iterate through the current stock, setting
             * the currentCoilLength each time. */
            if (!firstFitCoilStock.isEmpty()) {
                for (RopeCoil currentCoil : bestFitCoilStock) {
                    currentCoilLength = currentCoil.getRemainingLength();

                    // If the current order is shorter than or equal to the current coil, then it fits.
                    if (currentOrderLength <= currentCoilLength) {
                        ropeFits = true;

                        // If the current coil is shorter than the best fit, then make this coil the best fit.
                        if (currentCoilLength < bestFitLength) {
                            bestFitLength = currentCoilLength;
                            bestFitIndex = bestFitCoilStock.indexOf(currentCoil);
                        }
                    }
                }
            }
            try {
                /* If the order cant be cut from any of the current coils in stock, make a new coil
                 * cut the order from the new coil. */
                if (ropeFits == false || bestFitIndex == -1) {

                    // Take the next coil from the bank and increment the value of the bank index.
                    RopeCoil newCoil = new RopeCoil(coilBank.get(bankIndex++));
                    bestFitCoilStock.add(newCoil);
                    newCoil.cutRopeCoil(currentOrder);
                    BFCoilCounter += 1; // Increment the coil counter for the Best Fit algorithm.

                    // If the new coil is now too short, remove it from the current stock.
                    if (newCoil.isTooShort()) {
                        bestFitCoilStock.remove(newCoil);
                    }
                }
                // Else if a best fit was found, cut the order from this coil.
                else {
                    bestFit = bestFitCoilStock.get(bestFitIndex);
                    bestFit.cutRopeCoil(currentOrder);

                    // If the best fit coil is now too short, remove it from the current stock.
                    if (bestFit.isTooShort()) {
                        bestFitCoilStock.remove(bestFit);
                    }
                }
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the total number of coils used by the First Fit method.
     * @return integer FFCoilCounter.
     */
    public int getFFCoilCounter() {
        return this.FFCoilCounter;
    }

    /**
     * Get the total number of coils used by the Best Fit method.
     * @return integer BFCoilCounter.
     */
    public int getBFCoilCounter() {
        return this.BFCoilCounter;
    }

    /**
     * Get all current stock of rope coils for the Best Fit Algorithm.
     * @return a list of type RopeCoil.
     */
    public List<RopeCoil> getFirstFitCoilStock() {
        return this.firstFitCoilStock;
    }

    /**
     * Get all current stock of rope coils for the First Fit algorithm.
     * @return a list of type RopeCoil.
     */
    public List<RopeCoil> getBestFitCoilStock() {
        return this.bestFitCoilStock;
    }

    /**
    * Display a list of the current stock of rope coils.
    * @param coilList the list of coils to display.
    */
    public void displayCurrentStock(List<RopeCoil> coilList) {
        if (coilList.isEmpty())
            System.out.println("No current stock available.\n");
        else
            coilList.forEach(System.out::println);
    }

    /**
     * Get a list of all rope orders.
     * @return a list of type RopeOrder.
     */
    public List<RopeOrder> getAllOrders() {
        return this.allOrders;
    }

    /**
     * Display a list of all rope orders.
     */
    public void displayAllOrders() {
        if (getAllOrders().isEmpty())
            System.out.println("No orders available.");
        else
            getAllOrders().forEach(System.out::println);
    }

    /**
     * Generate a list of rope orders of random length within a given range.
     * @param quantity the number of orders to be generated.
     * @return list of type RopeOrder.
     */
    public List<RopeOrder> generateRopeOrders(int quantity) {
        List<RopeOrder> orders = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            RopeOrder ro = new RopeOrder();
            orders.add(ro);
        }
        return orders;
    }

    /**
     * Generate a list of rope coils of random length within a given range.
     * @param quantity the number of coils to be generated.
     * @return list of type RopeCoil.
     */
    public List<RopeCoil> generateRopeCoils(int quantity) {
        List<RopeCoil> coils = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            RopeCoil rc = new RopeCoil();
            coils.add(rc);
        }
        return coils;
    }

    /**
     * Get the total length of all rope coils ordered from the supplier.
     * @param coilList the list of coils to check.
     * @return int total length of rope coils.
     */
    public int getTotalOrderedRopeCoilLength(List<RopeCoil> coilList) {
        int totalLength = 0;
        for (RopeCoil rc : coilList)
            totalLength += rc.getStartLength();
        return totalLength;
    }

    /**
     * Get the total remaining length of all rope coils ordered from the supplier.
     * @param coilList the list of coils to check.
     * @return int total remaining length of rope coils.
     */
    public int getTotalRemainingRopeCoilLength(List<RopeCoil> coilList) {
        int totalRemLength = 0;
        for (RopeCoil rc : coilList)
            totalRemLength += rc.getRemainingLength();
        return totalRemLength;
    }

    /**
     * Get the total quantity of rope coils ordered from the supplier.
     * @param coilList the list of coils to check.
     * @return int total quantity of rope coils.
     */
    public int getTotalOrderedRopeCoilQuantity(List<RopeCoil> coilList) {
        int totalRopeCoils;
        totalRopeCoils = coilList.size();
        return totalRopeCoils;
    }
}
