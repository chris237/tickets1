package com.christian.tickets_syst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.christian.tickets_syst.entity.Ticket;
import com.christian.tickets_syst.entity.User;
import com.christian.tickets_syst.repository.TicketRepository;
import com.christian.tickets_syst.repository.UserRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository; // Nécessaire pour l'assignation d'un utilisateur

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

//    public Ticket createTicket(Ticket ticket) {
//        return ticketRepository.save(ticket);
//    }
    
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        User user = ticket.getUser();
        if (user.getId() == null) {
            userRepository.save(user); // Assurez-vous que l'utilisateur est sauvegardé
        }
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket ticket = getTicketById(id);
        ticket.setTitle(updatedTicket.getTitle());
        ticket.setDescription(updatedTicket.getDescription());
        ticket.setStatus(updatedTicket.getStatus());
        return ticketRepository.save(ticket);
    }

    // Nouvelle méthode pour assigner un ticket à un utilisateur
    public Ticket assignTicketToUser(Long ticketId, Long userId) {
        Ticket ticket = getTicketById(ticketId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }
    
    public List<Ticket> getTicketsByUserId(Long userId) {
        // Vérifie d'abord si l'utilisateur existe pour éviter les erreurs
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        // Récupère les tickets associés à cet utilisateur
        return ticketRepository.findByUserId(userId);
        
    }
}
