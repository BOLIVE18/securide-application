package com.securide.securide.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securide.securide.data.Moto;

public interface MotoRepository extends JpaRepository<Moto, UUID>{

}
