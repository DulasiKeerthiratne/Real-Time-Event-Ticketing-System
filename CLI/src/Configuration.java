import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

class negativeInputError extends Exception {
    public negativeInputError(String message) {
        super(message);
    }
}

public class Configuration implements Serializable {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration () {}

    public Configuration(Boolean promptForValues) {
        this.totalTickets = validate("Enter total tickets available: ");
        this.ticketReleaseRate = validate("Enter ticket release rate: ");
        this.customerRetrievalRate = validate("Enter customer retrieval rate: ");
        this.maxTicketCapacity = validate("Enter max ticket capacity: ");
    }

    public static int validate(String text) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;

        while (true) {
            try {
                System.out.print(text);
                num = scanner.nextInt();

                if (num < 0) {
                    throw new negativeInputError("Ticket count cannot be negative");
                } else {
                    System.out.println();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println( "Invalid input: Please enter an integer \n");
                scanner.next();
            } catch (negativeInputError e) {
                System.out.println( "Invalid input: Please enter an positive integer\n");
            }
        }
        return num;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString() {
        return "{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}
