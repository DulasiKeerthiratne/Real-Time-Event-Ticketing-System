import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration() {
    }

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        System.out.println("\n    Configuration Management");
        System.out.println("    ------------------------\n");
        this.totalTickets = validateConfig("   Enter total tickets available in ticket pool: ");
        this.ticketReleaseRate = validateConfig("   Enter ticket release rate(in seconds): ");
        this.customerRetrievalRate = validateConfig("   Enter customer retrieval rate(in seconds): ");
        this.maxTicketCapacity = validateConfig("   Enter max ticket capacity in ticket pool: ");
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
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }

    public static int validateConfig(String prompt){
        Scanner scanner = new Scanner(System.in);
        int config;
        while (true) {
            try {
                System.out.print(prompt);
                config = scanner.nextInt();

                if (config < 0) {
                    throw new NegativeInputError("Integer cannot be negative");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: Please enter an integer\n");
                scanner.next();  // clear the invalid input
            } catch (NegativeInputError e) {
                System.out.println("Invalid input: Please enter a positive integer\n");
            }
        }
        return config;
    }

    // Method to save the configuration to JSON file
    public void saveToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("configuration.json")) {
            gson.toJson(this, writer);
            System.out.println("\n>  Configuration saved");
        } catch (IOException e) {
            throw new RuntimeException("    \nFailed to save configuration", e);
        }
    }

    // Method to load a Configuration from JSON file
    public Configuration loadFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("configuration.json")) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException(" \nFailed to load configuration", e);
        }
    }
}

// custom error for negative input
class NegativeInputError extends Exception {
    public NegativeInputError(String message) {
        super(message);
    }
}
