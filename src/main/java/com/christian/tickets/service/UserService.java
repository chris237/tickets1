package com.christian.tickets.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.christian.tickets.entity.User;
import com.christian.tickets.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() { return userRepository.findAll(); }
    public User save(User user) { return userRepository.save(user); }
    public Optional<User> findById(Long id) { return userRepository.findById(id); }
    
}
