public class Ticket {
    private int ticketId;
    private String ticketType;
    private int ticketPrice;
    private String ticketVendor;
    private String sellTime;
    private String ticketCustomer;
    private String buyTime;

    public Ticket() {
    }

    public Ticket(String ticketType, int ticketPrice, String ticketVendor, String sellTime) {
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.ticketVendor = ticketVendor;
        this.sellTime = sellTime;
    }

    public Ticket(String ticketCustomer, String buyTime) {
        this.ticketCustomer = ticketCustomer;
        this.buyTime = buyTime;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketVendor() {
        return ticketVendor;
    }

    public void setTicketVendor(String ticketVendor) {
        this.ticketVendor = ticketVendor;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
    }

    public String getTicketCustomer() {
        return ticketCustomer;
    }

    public void setTicketCustomer(String ticketCustomer) {
        this.ticketCustomer = ticketCustomer;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    @Override
    public String toString() {
        return ("\n------------------------------------------------------------------------" +
                "Event: " + ticketType +
                "\nPrice: " + ticketPrice +
                "\nSold by: " + ticketVendor +
                "\nSold at: " + sellTime +
                "\nBought On: " + buyTime +
                "\n------------------------------------------------------------------------");
    }
}
