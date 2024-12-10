package com.dulasi.ticketsystem.backend.threads;


import com.dulasi.ticketsystem.backend.TicketPool;

public class VendorThread implements Runnable {
    private int vendorId;
    private int ticketReleaseRate;
    private TicketPool ticketPool;

    public VendorThread() {
    }

    public VendorThread(int ticketReleaseRate, TicketPool ticketPool) {
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
    }

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

    @Override
    public String toString() {
        return "VendorThread{" +
                "vendorId=" + vendorId +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", ticketPool=" + ticketPool +
                '}';
    }

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
