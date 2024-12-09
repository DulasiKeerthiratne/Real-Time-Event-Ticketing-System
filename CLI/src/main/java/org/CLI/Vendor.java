package org.CLI;

/**
 * The Vendor class represents a vendor in the ticketing system.
 *
 * @author Dulasi
 */
public class Vendor implements Runnable {
    private int vendorId;           // Unique identifier for the vendor
    private int ticketReleaseRate;  // Time interval (in seconds) for releasing tickets
    private TicketPool ticketPool;
    private int vendorCount;          // Number of vendors in the system

    //Default constructor for the Vendor class.
    public Vendor() {
    }

    /**
     * Parameterized constructor for the Vendor class.
     *
     * @param ticketReleaseRate  - Time interval (in seconds) for releasing tickets.
     * @param ticketPool         - Shared ticket pool.
     */
    public Vendor(int vendorNumber, int ticketReleaseRate, TicketPool ticketPool) {
        this.vendorCount = vendorNumber;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
    }

    // Getter and Setter methods
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorNumber) {
        this.vendorCount = vendorNumber;
    }

    /**
     * method for string representation of the vendor object.
     *
     * @return String representation of the vendor object.
     */
    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", ticketPool=" + ticketPool +
                '}';
    }

    //vendor thread
    @Override
    public void run() {
        while (ticketPool.getTotalTickets() >= 0) {
            ticketPool.addTicket();

            try {
                Thread.sleep(ticketReleaseRate * 1000); // Simulate time interval between ticket releases
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

