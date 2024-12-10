package com.dulasi.ticketsystem.backend.services;

import com.dulasi.ticketsystem.backend.TicketPool;
import com.dulasi.ticketsystem.backend.models.Configuration;
import com.dulasi.ticketsystem.backend.models.Ticket;
import com.dulasi.ticketsystem.backend.threads.CustomerThread;
import com.dulasi.ticketsystem.backend.threads.VendorThread;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {
    private Configuration config;

    public void start(Configuration configuration) {
        this.config = configuration;

        System.out.println("\n========================= Ticketing system started  ========================");

        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());

        for(int i = 1; i <= configuration.getVendorCount(); i++){
            VendorThread vendor = new VendorThread(config.getTicketReleaseRate(), ticketPool);
            Thread vendorThread = new Thread(vendor);
            vendorThread.setName("Vendor " + i);
            vendorThread.start();
        }

        for(int i = 1; i <= config.getCustomerCount(); i++){
            CustomerThread customer = new CustomerThread(config.getCustomerCount(), config.getCustomerRetrievalRate(), ticketPool);
            Thread customerThread = new Thread(customer);
            customerThread.setName("Customer " + i);
            customerThread.start();
        }
    }

    public void stop(Ticket config) {
    }
}
