package com.dulasi.ticketsystem.backend.threads;

import com.dulasi.ticketsystem.backend.TicketPool;

public class CustomerThread implements Runnable{
    private int customerID;
    private int customerRetrievalRate;
    private TicketPool ticketPool;

    public CustomerThread() {
    }

    public CustomerThread(int customerID, int customerRetrievalRate, TicketPool ticketPool) {
        this.customerID = customerID;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

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

    @Override
    public String toString() {
        return "CustomerThread{" +
                "customerID=" + customerID +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", ticketPool=" + ticketPool +
                '}';
    }

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
