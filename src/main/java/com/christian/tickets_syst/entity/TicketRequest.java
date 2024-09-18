package com.christian.tickets_syst.entity;

public class TicketRequest {
    private String title;
    private String description;
    private String status;
    private Long userId;  // Récupération de l'ID de l'utilisateur
    
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    // Getters et Setters
}

