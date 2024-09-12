package com.christian.tickets.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.christian.tickets.entity.Ticket;
import com.christian.tickets.entity.User;
import com.christian.tickets.service.TicketService;
import com.christian.tickets.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/tickets")
@Tag(name = "Ticket API", description = "API pour gérer les tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    private UserService userService;

    @Operation(summary = "Récupérer tous les tickets", description = "Renvoie la liste de tous les tickets enregistrés.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des tickets récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    @Operation(summary = "Récupérer un ticket par ID", description = "Renvoie un ticket spécifique en fonction de son ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket récupéré avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket non trouvé", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.findById(id).orElseThrow();
    }

    @Operation(summary = "Créer un nouveau ticket", description = "Permet de créer un ticket.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @Operation(summary = "Mettre à jour un ticket", description = "Permet de modifier les détails d'un ticket existant.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket non trouvé", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketService.findById(id).orElseThrow();
        ticket.setTitle(ticketDetails.getTitle());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatus(ticketDetails.getStatus());
        return ticketService.save(ticket);
    }

    @Operation(summary = "Assigner un ticket à un utilisateur", description = "Permet d'assigner un ticket à un utilisateur spécifique.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket assigné avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket ou utilisateur non trouvé", content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}/assign/{userId}")
    public Ticket assignTicketToUser(@PathVariable Long id, @PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow();
        ticketService.assignTicketToUser(id, user);
        return ticketService.findById(id).orElseThrow();
    }

    @Operation(summary = "Supprimer un ticket", description = "Permet de supprimer un ticket en fonction de son ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Ticket non trouvé", content = @Content(schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.findById(id).ifPresent(ticket -> ticketService.delete(ticket));
    }
}
