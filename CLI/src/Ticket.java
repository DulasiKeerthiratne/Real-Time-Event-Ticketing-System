public class Ticket {
    private String ticketId;
    private String ticketType;
    private String ticketPrice;
    private String vendorNumber;
    private String sellTime;
    private String customerNumber;
    private String buyTime;

    public void createTicket(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public void buyTicket(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
