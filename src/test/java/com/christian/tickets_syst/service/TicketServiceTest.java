package com.christian.tickets_syst.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.christian.tickets_syst.entity.Ticket;
import com.christian.tickets_syst.repository.TicketRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a dummy ticket for testing
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTitle("Sample Ticket");
    }

    @Test
    void testGetTicketById() {
        // Define behavior for ticketRepository
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Call the method to test
        Ticket foundTicket = ticketService.getTicketById(1L);

        // Verify the result
        assertNotNull(foundTicket);
        assertEquals("Sample Ticket", foundTicket.getTitle());

        // Verify interaction with repository
        verify(ticketRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTicket() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket savedTicket = ticketService.createTicket(ticket);

        assertNotNull(savedTicket);
        assertEquals("Sample Ticket", savedTicket.getTitle());

        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testDeleteTicketById() {
        ticketService.deleteTicketById(1L);

        verify(ticketRepository, times(1)).deleteById(1L);
    }
}
