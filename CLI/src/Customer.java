public class Customer implements Runnable {
    public int customerRetrievalRate;
    private TicketPool ticketPool;

    public Customer() {
    }

    public Customer(int customerRetrievalRate, TicketPool ticketPool) {
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
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
        return "Customer{" +
                "customerRetrievalRate=" + customerRetrievalRate +
                ", ticketPool=" + ticketPool +
                '}';
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            ticketPool.removeTicket(i);
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
