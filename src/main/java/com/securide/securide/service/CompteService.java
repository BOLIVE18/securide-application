package com.securide.securide.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.securide.securide.back.FunctionalIssue;
import com.securide.securide.back.SimpleFunctionalIssue;
import com.securide.securide.data.Compte;
import com.securide.securide.data.Utilisateur;
import com.securide.securide.repository.CompteRepository;

import io.vavr.control.Either;

@Service
public class CompteService {
	private final CompteRepository compteRepository;
	
	@Autowired
	public CompteService(CompteRepository compteRepository) {
		this.compteRepository = compteRepository;
	}
	
	public Either<FunctionalIssue, Optional<Compte>> getCompteByUuid(UUID uuid){
		if(compteRepository.getOne(uuid) == null) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(compteRepository.findById(uuid));
	}
	
	public Either<FunctionalIssue, List<Compte>> getAllComptes(){
		List<Compte> listCompte = compteRepository.findAll();
		return Either.right(listCompte);
	}
	
	public Either<FunctionalIssue, Compte> createCompte(Compte compte){
		return Either.right(compteRepository.save(compte));
	}
}
