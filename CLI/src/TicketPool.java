public class TicketPool {
    private int ticketsInPool; //current number of tickets in pool

    public TicketPool() {
    }

    public TicketPool(int totalTickets, int ticketsInPool) {
        this.ticketsInPool = ticketsInPool;
    }

    public int getTicketsInPool() {
        return ticketsInPool;
    }

    public void setTicketsInPool(int ticketsInPool) {
        this.ticketsInPool = ticketsInPool;
    }

    @Override
    public String toString() {
        return "TicketPool{" +
                ", ticketsInPool=" + ticketsInPool +
                '}';
    }

    public synchronized void addTicket(){
        this.ticketsInPool++;
        System.out.println("Ticket sold by " + Thread.currentThread().getName() + " - Ticket Pool has " + getTicketsInPool() + " Ticket(s)");
    }

    public synchronized void removeTicket(int customerNumber){
        this.ticketsInPool--;
        System.out.println("Ticket bought by " + Thread.currentThread().getName() + " - Ticket Pool has " + getTicketsInPool() + " Ticket(s)");
    }
}
