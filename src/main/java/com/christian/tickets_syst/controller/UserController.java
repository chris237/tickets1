package com.christian.tickets_syst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.christian.tickets_syst.entity.Ticket;
import com.christian.tickets_syst.entity.User;
import com.christian.tickets_syst.service.UserService;
import com.christian.tickets_syst.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Obtenir tous les utilisateurs", description = "Récupérer la liste de tous les utilisateurs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Créer un nouvel utilisateur", description = "Créer un nouvel utilisateur et retourner l'utilisateur créé")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Entrée invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @Operation(summary = "Mettre à jour un utilisateur", description = "Mettre à jour un utilisateur existant par ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
        @ApiResponse(responseCode = "400", description = "Entrée invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Obtenir les tickets d'un utilisateur", description = "Récupérer les tickets associés à un utilisateur par ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des tickets récupérée avec succès"),
        @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getUserTickets(@PathVariable Long id) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(id);
        return ResponseEntity.ok(tickets);
    }
}
