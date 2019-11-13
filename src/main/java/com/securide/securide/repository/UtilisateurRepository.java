package com.securide.securide.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.securide.securide.data.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, UUID>{
	@Query("SELECT utilisateur FROM Utilisateur utilisateur WHERE (utilisateur.email = :email) and (utilisateur.mdp = :mdp)")
	Optional<Utilisateur> findByEmailAndMdp(String email, String mdp);
}
