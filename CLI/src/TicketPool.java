import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class TicketPool {
    int totalTickets;
    private int maxTicketCapacity;
    private List<Ticket> ticketPool = Collections.synchronizedList(new ArrayList<>());

    public TicketPool() {
    }

    public TicketPool(int totalTickets, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public synchronized int getTotalTickets() {
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

    public List<Ticket> getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(List<Ticket> ticketPool) {
        this.ticketPool = ticketPool;
    }

    public String dateTime() {
        Date date = new Date();
        SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        String sellTime = day.format(date) + " at " + time.format(date);
        return sellTime;
    }

    public synchronized void addTicket() {
        while (ticketPool.size() > maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket vendorTicket = new Ticket("Concert Ticket", 50000, Thread.currentThread().getName(), dateTime());
        this.ticketPool.add(vendorTicket);
        this.totalTickets--;
        System.out.println("\nTicket sold by " + Thread.currentThread().getName() + ": Ticket Pool has " + ticketPool.size() + " Ticket(s)");
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
        // Once a ticket is available, proceed with the purchase
        Ticket customerTicket = ticketPool.remove(0);
        customerTicket.setTicketCustomer(Thread.currentThread().getName());
        customerTicket.setSellTime(dateTime());
        System.out.println("\nTicket bought by " + Thread.currentThread().getName() + ": Ticket Pool has " + ticketPool.size() + " Ticket(s)" +
                "\n Ticket bought: " + customerTicket.toString());
        notifyAll();
    }
}
