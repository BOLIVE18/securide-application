package com.securide.securide.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.securide.securide.back.FunctionalIssue;
import com.securide.securide.back.SimpleFunctionalIssue;
import com.securide.securide.data.Utilisateur;
import com.securide.securide.repository.UtilisateurRepository;

import io.vavr.control.Either;

@Service
public class UtilisateurService {
	private final UtilisateurRepository utilisateurRepository;
	
	@Autowired
	public UtilisateurService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	public Either<FunctionalIssue, Optional<Utilisateur>> getUtilisateurByUuid(UUID uuid){
		if(utilisateurRepository.getOne(uuid) == null) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(utilisateurRepository.findById(uuid));
	}
	
	public Either<FunctionalIssue, Optional<Utilisateur>> getUtilisateurByMailAndMdp(String email, String mdp){
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmailAndMdp(email, mdp);
		if(!optionalUtilisateur.isPresent()) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(optionalUtilisateur);
	}
	
	public Either<FunctionalIssue, List<Utilisateur>> getAllUtilisateur(){
		List<Utilisateur> listUtilisateur = utilisateurRepository.findAll();
		return Either.right(listUtilisateur);
	}
	
	public Either<FunctionalIssue, Utilisateur> createUtilisateur(Utilisateur utilisateur){
		if(utilisateur.getMdp().length() < 5) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "MDP_NEED_TO_BE_SUPP_AS_FIVE"));
		}
		return Either.right(utilisateurRepository.save(utilisateur));
	}
}
