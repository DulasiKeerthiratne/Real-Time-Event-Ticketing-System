public class Main {
    public static void main(String[] args) {
        //create a new configuration
        Configuration config = new Configuration(0, 0, 0, 0);

        //save the configuration from file
        config.saveToFile();

        //load the configuration from file
        Configuration loadedConfig = config.loadFromFile();
        System.out.println("\n> Loaded configuration: " + loadedConfig);

        //create ticket pool
        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), 0);

        for(int i = 1; i <= 3; i++){
            Vendor vendor = new Vendor(config.getTotalTickets(), config.getTicketReleaseRate(), ticketPool);
            Thread vendorThread = new Thread(vendor);
            vendorThread.setName("Vendor " + i);
            vendorThread.start();
        }
    }
}