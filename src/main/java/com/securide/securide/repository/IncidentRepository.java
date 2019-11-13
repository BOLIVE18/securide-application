package com.securide.securide.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securide.securide.data.Incident;

public interface IncidentRepository extends JpaRepository<Incident, UUID>{

}
