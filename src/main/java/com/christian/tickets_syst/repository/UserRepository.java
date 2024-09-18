package com.christian.tickets_syst.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.christian.tickets_syst.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
