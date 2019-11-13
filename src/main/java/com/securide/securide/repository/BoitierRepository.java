package com.securide.securide.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securide.securide.data.Boitier;

public interface BoitierRepository extends JpaRepository<Boitier ,UUID>{

}
