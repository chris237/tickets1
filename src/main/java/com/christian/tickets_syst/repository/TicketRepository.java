package com.christian.tickets_syst.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.christian.tickets_syst.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);

}