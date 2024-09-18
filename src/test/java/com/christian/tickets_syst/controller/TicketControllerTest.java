package com.christian.tickets_syst.controller;

import com.christian.tickets_syst.entity.Ticket;
import com.christian.tickets_syst.entity.TicketRequest;
import com.christian.tickets_syst.service.TicketService;
import com.christian.tickets_syst.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.christian.tickets_syst.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TicketController ticketController;

    private List<Ticket> ticketList;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks
        MockitoAnnotations.openMocks(this);

        // Création d'une liste factice de tickets pour les tests
        ticketList = new ArrayList<>();
        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        ticket1.setTitle("Ticket 1");

        Ticket ticket2 = new Ticket();
        ticket2.setId(2L);
        ticket2.setTitle("Ticket 2");

        ticketList.add(ticket1);
        ticketList.add(ticket2);
    }

    @Test
    void testGetAllTickets() {
        // Définir le comportement de ticketService
        when(ticketService.getAllTickets()).thenReturn(ticketList);

        // Appeler la méthode à tester
        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets();

        // Vérifier le résultat
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        // Vérifier l'interaction avec ticketService
        verify(ticketService, times(1)).getAllTickets();
    }

    @Test
    void testGetTicketById_Success() {
        // Ticket existant
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Ticket 1");

        when(ticketService.getTicketById(1L)).thenReturn(ticket);

        // Tester la méthode
        ResponseEntity<Ticket> response = ticketController.getTicketById(1L);

        // Vérifications
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ticket 1", response.getBody().getTitle());

        verify(ticketService, times(1)).getTicketById(1L);
    }

    @Test
    void testGetTicketById_NotFound() {
        // Simuler un ticket non trouvé
        when(ticketService.getTicketById(1L)).thenReturn(null);

        // Tester la méthode
        ResponseEntity<Ticket> response = ticketController.getTicketById(1L);

        // Vérifications
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(ticketService, times(1)).getTicketById(1L);
    }

    @Test
    void testCreateTicket_Success() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("New Ticket");

        // Simuler la création de ticket
        when(ticketService.createTicket(any(Ticket.class))).thenReturn(ticket);

        TicketRequest request = new TicketRequest();
        request.setTitle("New Ticket");
        request.setDescription("This is a new ticket");
        request.setUserId(1L);

        // Simuler l'utilisateur existant
        when(userService.getUserById(1L)).thenReturn(new User());

        // Tester la méthode
        ResponseEntity<Ticket> response = ticketController.createTicket(request);

        // Vérifications
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("New Ticket", response.getBody().getTitle());

        verify(ticketService, times(1)).createTicket(any(Ticket.class));
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateTicket_UserNotFound() {
        // Simuler que l'utilisateur n'existe pas
        when(userService.getUserById(1L)).thenReturn(null);

        TicketRequest request = new TicketRequest();
        request.setTitle("New Ticket");
        request.setDescription("This is a new ticket");
        request.setUserId(1L);

        // Tester la méthode
        ResponseEntity<Ticket> response = ticketController.createTicket(request);

        // Vérifier que la création échoue avec un statut 400
        assertEquals(400, response.getStatusCodeValue());

        // Vérifier que ticketService.createTicket n'est pas appelé
        verify(ticketService, times(0)).createTicket(any(Ticket.class));
    }
}
