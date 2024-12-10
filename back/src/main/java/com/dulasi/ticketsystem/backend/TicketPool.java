package com.dulasi.ticketsystem.backend;

import com.dulasi.ticketsystem.backend.models.Ticket;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketPool {
    private int totalTickets;
    private int maxTicketCapacity;
    private ConcurrentLinkedQueue<Ticket> ticketPool = new ConcurrentLinkedQueue<>();

    public TicketPool() {
    }

    public TicketPool(int totalTickets, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public ConcurrentLinkedQueue<Ticket> getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(ConcurrentLinkedQueue<Ticket> ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public String toString() {
        return "TicketPool{" +
                "totalTickets=" + totalTickets +
                ", maxTicketCapacity=" + maxTicketCapacity +
                ", ticketPool=" + ticketPool +
                '}';
    }

    public String dateTime() {
        Date date = new Date();
        SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        return day.format(date) + " at " + time.format(date);
    }

    public synchronized void addTicket() {
        while (ticketPool.size() >= maxTicketCapacity || totalTickets <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket vendorTicket = new Ticket("Concert Ticket", new BigDecimal("1000.00"), Thread.currentThread().getName(), dateTime());
        ticketPool.offer(vendorTicket); // Non-blocking add to queue
        totalTickets--; // Decrease total tickets only when a ticket is added
        System.out.println("\n    Ticket sold by " + Thread.currentThread().getName() + "\n    Ticket Pool has " + ticketPool.size() + " Ticket(s)");
        notifyAll();
    }

    public synchronized void removeTicket() {
        while (ticketPool.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket customerTicket = ticketPool.poll();
        customerTicket.setTicketCustomer(Thread.currentThread().getName());
        customerTicket.setBuyTime(dateTime());
        System.out.println("\n    Ticket bought by " + Thread.currentThread().getName() + "\n    Ticket Pool has " + ticketPool.size() + " Ticket(s)");
        notifyAll();
    }
}
