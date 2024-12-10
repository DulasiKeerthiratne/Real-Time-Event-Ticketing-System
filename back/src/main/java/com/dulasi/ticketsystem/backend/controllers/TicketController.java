package com.dulasi.ticketsystem.backend.controllers;

import com.dulasi.ticketsystem.backend.models.Ticket;
import com.dulasi.ticketsystem.backend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value= "api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value= "/save")
    public String saveStudent(@RequestBody Ticket ticket){
        ticketService.saveOrUpdate(ticket);
        return ticket.getId();
    }

    @GetMapping(value= "/getAll")
    public Iterable<Ticket> getAllStudents(){
        return ticketService.listAll();
    }

    @PutMapping(value= "/edit/{id}")
    public String editTicket(@PathVariable(name= "id") String id, @RequestBody Ticket ticket){
        ticket.setId(id);
        ticketService.saveOrUpdate(ticket);
        return ticket.getId();
    }

    @DeleteMapping(value= "/delete/{id}")
    public void deleteTicket(@PathVariable("id") String id){
        ticketService.delete(id);
    }

    @RequestMapping(value= "/search/{id}")
    public Ticket getTicket(@PathVariable(name= "id") String id){
        return ticketService.getTicketByID(id);
    }
}
