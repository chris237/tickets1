package com.christian.tickets_syst.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.christian.tickets_syst.entity.User;
import com.christian.tickets_syst.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        // Initialiser les mocks
        MockitoAnnotations.openMocks(this);

        // Créer un utilisateur fictif pour les tests
        user = new User();
        user.setId(1L);
        user.setUsername("chris");
        user.setEmail("chris@gmail.com");
    }

    @Test
    void testGetUserById() {
        // Simuler le comportement de userRepository.findById
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Appeler la méthode à tester
        User foundUser = userService.getUserById(1L);

        // Vérifier le résultat
        assertNotNull(foundUser);
        assertEquals("chris", foundUser.getUsername());
        assertEquals("chris@gmail.com", foundUser.getEmail());

        // Vérifier que le repository a bien été appelé
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() {
        // Simuler le comportement de userRepository.save
        when(userRepository.save(user)).thenReturn(user);

        // Appeler la méthode à tester
        User createdUser = userService.createUser(user);

        // Vérifier le résultat
        assertNotNull(createdUser);
        assertEquals("chris", createdUser.getUsername());

        // Vérifier que le repository a bien été appelé
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        // Simuler le comportement de userRepository.findById et save
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Appeler la méthode à tester
        User updatedUser = new User();
        updatedUser.setUsername("alex");
        updatedUser.setEmail("alex@gmail.com");

        User result = userService.updateUser(1L, updatedUser);

        // Vérifier que les informations ont été mises à jour
        assertEquals("alex", result.getUsername());
        assertEquals("alex@gmail.com", result.getEmail());

        // Vérifier que le repository a bien été appelé
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUserById_NotFound() {
        // Simuler le comportement où l'utilisateur n'existe pas
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Appeler la méthode à tester
        User foundUser = userService.getUserById(1L);

        // Vérifier que le résultat est nul lorsque l'utilisateur n'est pas trouvé
        assertNull(foundUser);

        // Vérifier que le repository a bien été appelé
        verify(userRepository, times(1)).findById(1L);
    }
}
