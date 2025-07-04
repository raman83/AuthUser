package com.authuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authuser.entity.CustomerCredential;


public interface CustomerCredentialRepository extends JpaRepository<CustomerCredential, Long> {
    Optional<CustomerCredential> findByEmail(String email);
}