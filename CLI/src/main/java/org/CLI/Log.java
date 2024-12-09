package org.CLI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    private File logDir;
    private String fileName;
    private Configuration config;
    private int customerCount;

    public Log() {
        // Empty constructor
    }

    public Log(Configuration config, int customer) {
        this.config = config;
        this.customerCount = customerCount;
    }

    public void fileStarter() {
        // Initialize the log directory
        logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        Date date = new Date();
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-SSS");
        this.fileName = "logs/" + dateTime.format(date) + ".txt";

        File logFile = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("------------------------------------------------------------------------\n" +
                             "                     Ticket Pool Purchase History\n                     " +
                             "------------------------------------------------------------------------\n\n");
            writer.write("Number of tickets: " + config.getTotalTickets() +
                             "\nNumber of Vendors: " + customerCount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method for logging the ticket details
    public void logging(Ticket ticket) {
        String logMessage = String.format(
                "    ------------------------------------------------------------------------\n" +
                        "    Event       : " + ticket.getTicketType() + "\n" +
                        "    Price       : " + (ticket.getTicketPrice()) + "\n" +
                        "    Sold by     : " + ticket.getTicketVendor() + "\n" +
                        "    Bought by   : " + ticket.getTicketCustomer() + "\n" +
                        "    Sold at     : " + ticket.getSellTime() + "\n" +
                        "    Bought On   : " + ticket.getBuyTime() + "\n" +
                "    ------------------------------------------------------------------------");

        // Log the message using log4j
        logger.info("Purchase Information\n" + logMessage);

        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("\n" +logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
