package com.christian.tickets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christian.tickets.entity.Ticket;
import com.christian.tickets.entity.User;
import com.christian.tickets.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> findAll() { return ticketRepository.findAll(); }
    public Ticket save(Ticket ticket) { return ticketRepository.save(ticket); }
    public Optional<Ticket> findById(Long id) { return ticketRepository.findById(id); }
    public void assignTicketToUser(Long ticketId, User user) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }
    
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
