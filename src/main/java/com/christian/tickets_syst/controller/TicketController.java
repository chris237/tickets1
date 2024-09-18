package com.christian.tickets_syst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.christian.tickets_syst.entity.Status;
import com.christian.tickets_syst.entity.Ticket;
import com.christian.tickets_syst.entity.TicketRequest;
import com.christian.tickets_syst.entity.User;
import com.christian.tickets_syst.service.TicketService;
import com.christian.tickets_syst.service.UserService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Obtenir tous les tickets", description = "Récupérer la liste de tous les tickets")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des tickets récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Obtenir un ticket par ID", description = "Récupérer un ticket par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket récupéré avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Créer un nouveau ticket", description = "Créer un nouveau ticket et retourner le ticket créé")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ticket créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Entrée invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticketRequest) {
        User user = userService.getUserById(ticketRequest.getUserId());
        
        if (user == null) {
            return ResponseEntity.badRequest().build(); // Retourne une erreur si l'utilisateur n'existe pas
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        
        // Vérifiez si le statut est valide avant de le définir
        try {
            Status status = Status.valueOf(ticketRequest.getStatus().toUpperCase());
            ticket.setStatus(status);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Retourne une erreur si le statut est invalide
        }
        
        ticket.setUser(user);
        Ticket savedTicket = ticketService.createTicket(ticket);

        return ResponseEntity.status(201).body(savedTicket);
    }

    @Operation(summary = "Mettre à jour un ticket", description = "Mettre à jour un ticket existant par ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket non trouvé"),
        @ApiResponse(responseCode = "400", description = "Entrée invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @Operation(summary = "Assigner un ticket à un utilisateur", description = "Assigner un ticket à un utilisateur spécifique par ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket assigné avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket ou utilisateur non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{id}/assign/{userId}")
    public ResponseEntity<Ticket> assignTicketToUser(@PathVariable Long id, @PathVariable Long userId) {
        Ticket assignedTicket = ticketService.assignTicketToUser(id, userId);
        return ResponseEntity.ok(assignedTicket);
    }

    @Operation(summary = "Supprimer un ticket", description = "Supprimer un ticket par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Ticket supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.noContent().build();
    }
}
