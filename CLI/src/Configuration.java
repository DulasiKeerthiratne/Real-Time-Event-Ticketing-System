import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Custom error for negative input
class NegativeInputError extends Exception {
    public NegativeInputError(String message) {
        super(message);
    }
}

public class Configuration {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private static final Gson gson = new Gson();

    // Empty constructor
    public Configuration() {}

    // Constructor that prompts user for values
    public Configuration(Boolean promptForValues) {
        this.totalTickets = validate("Enter total tickets available: ");
        this.ticketReleaseRate = validate("Enter ticket release rate: ");
        this.customerRetrievalRate = validate("Enter customer retrieval rate: ");
        this.maxTicketCapacity = validate("Enter max ticket capacity: ");
    }

    // Validation method for user input
    public static int validate(String text) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;

        while (true) {
            try {
                System.out.print(text);
                num = scanner.nextInt();

                if (num < 0) {
                    throw new NegativeInputError("Ticket count cannot be negative");
                } else {
                    System.out.println();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: Please enter an integer\n");
                scanner.next();  // Clear the invalid input
            } catch (NegativeInputError e) {
                System.out.println("Invalid input: Please enter a positive integer\n");
            }
        }
        return num;
    }

    // Method to save the configuration to JSON file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("configuration.json")) {
            gson.toJson(this, writer);
            System.out.println("Configuration saved");
        } catch (IOException e) {
            throw new RuntimeException("Failed to save configuration", e);
        }
    }

    // Method to load a Configuration from JSON file
    public Configuration loadFromFile() {
        try (FileReader reader = new FileReader("configuration.json")) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
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
