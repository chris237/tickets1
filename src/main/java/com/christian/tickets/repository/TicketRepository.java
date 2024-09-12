package com.christian.tickets.repository;

import org.springframework.stereotype.Repository;

import com.christian.tickets.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
