package org.CLI;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Configuration class is used for configuring settings for the ticketing system.
 * This class will take in validated userInput to configure settings.
 * This class will save and load configurations from a JSON file using Gson
 *
 * @author Dulasi
 */
public class Configuration {

    private int totalTickets;           // Total number of tickets in the pool
    private int ticketReleaseRate;     // Time interval (in seconds) for ticket release by vendors
    private int customerRetrievalRate; // Time interval (in seconds) for customers to retrieve tickets
    private int maxTicketCapacity;     // Maximum ticket capacity allowed in the ticket pool
    private int vendorNumber;          // Number of vendors in the system
    private int customerNumber;        // Number of customers in the system


    //Default constructor for Configuration.
    public Configuration() {
    }

    /**
     * Parameterized constructor for Configuration.
     * Constructor that takes in validated userInput to configure settings
     *
     * @param totalTickets           - Total number of tickets available.
     * @param ticketReleaseRate      - Rate at which tickets are released (in seconds).
     * @param customerRetrievalRate  - Rate at which customers retrieve tickets (in seconds).
     * @param maxTicketCapacity      - Maximum ticket pool capacity.
     * @param vendorNumber           - Number of vendors.
     * @param customerNumber         - Number of customers.
     */
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int vendorNumber, int customerNumber) {
        System.out.println("\n    Configuration Management");
        System.out.println("    ------------------------\n");

        // Validate user input for each configuration parameter
        this.totalTickets = validateConfig("   Enter total tickets available in ticket pool: ");
        this.ticketReleaseRate = validateConfig("   Enter ticket release rate (in seconds): ");
        this.customerRetrievalRate = validateConfig("   Enter customer retrieval rate (in seconds): ");
        this.maxTicketCapacity = validateConfig("   Enter max ticket capacity in ticket pool: ");
        this.vendorNumber = validateConfig("   Enter number of vendors: ");
        this.customerNumber = validateConfig("   Enter number of customers: ");
    }

    // Getter and Setter methods
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

    public int getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(int vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     * method for string representation of the configuration object.
     *
     * @return String representation of the configuration object.
     */
    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                ", vendorNumber=" + vendorNumber +
                ", customerNumber=" + customerNumber +
                '}';
    }

    /**
     * Prompts the user to input a configuration value that is valid.
     * Ensures the input is valid and by handling invalid and negative input.
     *
     * @param prompt - Prompt message displayed to the user.
     * @return Validated integer configuration value.
     */
    public static int validateConfig(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int config;

        while (true) {
            try {
                System.out.print(prompt);
                config = scanner.nextInt();

                // Custom validation: Value must be non-negative
                if (config < 0) {
                    throw new NegativeInputError("Integer cannot be negative");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input: Please enter an integer\n");
                scanner.next(); // Clear invalid input
            } catch (NegativeInputError e) {
                System.out.println("Invalid input: " + e.getMessage() + "\n");
            }
        }

        return config;
    }

    //Saves the current configuration object.
    public void saveToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("configuration.json")) {
            gson.toJson(this, writer);
            System.out.println("\n> Configuration saved successfully.");
        } catch (IOException e) {
            throw new RuntimeException("\nFailed to save configuration", e);
        }
    }

    /**
     * Loads a configuration object.
     *
     * @return Configuration object loaded from the file.
     */
    public Configuration loadFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("configuration.json")) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("\nFailed to load configuration", e);
        }
    }
}

//Custom exception to handle negative input errors.
class NegativeInputError extends Exception {
    public NegativeInputError(String message) {
        super(message);
    }
}