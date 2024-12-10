package com.dulasi.ticketsystem.backend.services;

import com.dulasi.ticketsystem.backend.models.Ticket;
import com.dulasi.ticketsystem.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public void saveOrUpdate(Ticket config) {
        ticketRepository.save(config);
    }

    public Iterable<Ticket> listAll() {
        return this.ticketRepository.findAll();
    }

    public void delete(String id) {
        ticketRepository.deleteById(id);
    }

    public Ticket getTicketByID(String id){
        return this.ticketRepository.findById(id).get();
    }
}
