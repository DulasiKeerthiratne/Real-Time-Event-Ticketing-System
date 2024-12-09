package org.CLI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Configuration config = new Configuration();

        System.out.print("""
                \n                                 TICKETING SYSTEM
                    ========================= Configuration Management ========================
                    
                    1. Set new system Configuration
                    2. Use Loaded system Configuration
                    
                """);

        while (true) {
            System.out.print("    Choose Option: ");
            try{
                int userSelect = scanner.nextInt();

                if (userSelect == 1) {
                    System.out.println("\n    ===========================================================================");
                    config = new Configuration(0, 0, 0, 0);
                    System.out.println("\n    ===========================================================================");
                    scanner.nextLine();

                    System.out.print("\n    Save Configuration? (Y/N): ");
                    while (true) {
                        String userOption = scanner.nextLine();  // Read the entire line

                        if (userOption.equalsIgnoreCase("Y")) {
                            config.saveToFile();
                            break;
                        } else if (userOption.equalsIgnoreCase("N")) {
                            break;  // Exit the loop without saving
                        } else {
                            System.out.println("    Invalid option: Please choose option Y or N");
                        }
                    }
                    break;


                } else if (userSelect == 2) {
                    config = config.loadFromFile();
                    System.out.println("\n    ===========================================================================");
                    System.out.println("\n    " + config.toString());
                    System.out.println("\n    ===========================================================================");
                    break;
                } else {
                    System.out.println("    Invalid option: Please choose option 1 0r 2\n");
                }
            } catch (InputMismatchException e){
                System.out.println("    Invalid option: Please choose option 1 0r 2\n");
                scanner.next();
            }
        }

        int vendorCount = config.validateConfig("    Enter Number of vendors: ");
        int customerCount = config.validateConfig("    Enter Number of customers: ");

        System.out.println("\n    ===========================================================================");

        Log logger = new Log(config, customerCount);
        logger.fileStarter();
        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity(), logger);

        for(int i = 1; i <= vendorCount; i++){
            Vendor vendor = new Vendor(vendorCount, config.getTicketReleaseRate(), ticketPool);
            Thread vendorThread = new Thread(vendor);
            vendorThread.setName("Vendor " + i);
            vendorThread.start();
        }

        for(int i = 1; i <= customerCount; i++){
            Customer customer = new Customer(customerCount, config.getCustomerRetrievalRate(), ticketPool);
            Thread customerThread = new Thread(customer);
            customerThread.setName("Customer " + i);
            customerThread.start();
        }
    }
}