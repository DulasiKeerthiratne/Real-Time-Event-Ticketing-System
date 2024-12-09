package org.CLI;

public class Main {
    public static void main(String[] args) {

        //Create a new configuration
        Configuration config = new Configuration(0, 0, 0, 0, 0, 0);
        Log logger = new Log(config);
        logger.fileStarter();

        //Save the configuration from file
        config.saveToFile();

        //Load the configuration from file
        Configuration loadedConfig = config.loadFromFile();
        System.out.println("> Loaded configuration: " + loadedConfig);

        //Create a new ticket pool
        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity(), logger);

        //Vendor thread
        for(int i = 1; i <= config.getVendorNumber(); i++){
            Vendor vendor = new Vendor(config.getVendorNumber(), config.getTicketReleaseRate(), ticketPool);
            Thread vendorThread = new Thread(vendor);
            vendorThread.setName("Vendor " + i);
            vendorThread.start();
        }

        for(int i = 1; i <= config.getCustomerNumber(); i++){
            Customer customer = new Customer(config.getCustomerRetrievalRate(), ticketPool);
            Thread customerThread = new Thread(customer);
            customerThread.setName("Customer " + i);
            customerThread.start();
        }
    }
}