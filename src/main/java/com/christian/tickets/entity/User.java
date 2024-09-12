package com.christian.tickets.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();
    
    public String getUsername() {
		return username;
	}
    
    public void setUsername(String username) {
		this.username = username;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getEmail() {
		return email;
	}
    public List<Ticket> getTickets() {
		return tickets;
	}
}
