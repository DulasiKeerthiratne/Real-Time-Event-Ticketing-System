package org.CLI;

/**
 * The Customer class represents a Customer in the ticketing system.
 *
 * @author Dulasi
 */
public class Customer implements Runnable {
    private int customerID;            // Unique identifier for the customer
    private int customerRetrievalRate; // Time interval (in seconds) for retrieving tickets
    private TicketPool ticketPool;     //Shared ticket pool
    private int customerCount;

    //Default constructor for the Vendor class.
    public Customer() {
    }

    /**
     * Parameterized constructor for the Vendor class.
     *
     * @param customerRetrievalRate  - Time interval (in seconds) for retrieving tickets.
     */
    public Customer(int customerCount, int customerRetrievalRate, TicketPool ticketPool) {
        this.customerCount = customerCount;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    // Getter and Setter methods
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    /**
     * method for string representation of the customer object.
     *
     * @return String representation of the customer object.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", ticketPool=" + ticketPool +
                '}';
    }

    //customer thread
    @Override
    public void run() {
        while (true) {
            ticketPool.removeTicket(); // Attempt to buy a ticket
            try {
                Thread.sleep(customerRetrievalRate * 1000); // Simulate the time interval for the customer
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

