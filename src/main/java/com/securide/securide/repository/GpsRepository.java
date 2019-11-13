package com.securide.securide.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securide.securide.data.Gps;

public interface GpsRepository extends JpaRepository<Gps, UUID> {

}
