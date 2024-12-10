package com.dulasi.ticketsystem.backend.repositories;

import com.dulasi.ticketsystem.backend.models.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
