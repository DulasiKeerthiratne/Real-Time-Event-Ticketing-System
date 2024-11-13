import java.util.InputMismatchException;
import java.util.Scanner;

class negativeInputError extends Exception {
    public negativeInputError(String message) {
        super(message);
    }
}

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int totalTickets = validate("Enter total tickets available: ");
        int ticketReleaseRate = validate("Enter ticket release rate: ");
        int customerRetrievalRate = validate("Enter customer retrieval rate: ");
        int maxTicketCapacity = validate("Enter max ticket capacity: ");
    }

    public static int validate(String text) {
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
}
