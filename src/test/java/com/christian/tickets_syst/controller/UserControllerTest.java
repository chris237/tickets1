package com.christian.tickets_syst.controller;

import com.christian.tickets_syst.entity.User;
import com.christian.tickets_syst.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("chris");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("alex");

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

        verify(userService, times(1)).getAllUsers();
    }
}
