package com.christian.tickets_syst.exception;

public class UserNotFoundException extends RuntimeException {

    // Ajout du champ serialVersionUID pour la sérialisation
    private static final long serialVersionUID = 1L;

    // Constructeur par défaut avec un message prédéfini
    public UserNotFoundException() {
        super("User not found");
    }

    // Constructeur avec un message personnalisé
    public UserNotFoundException(String message) {
        super(message);
    }
}