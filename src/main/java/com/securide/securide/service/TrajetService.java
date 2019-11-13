package com.securide.securide.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.securide.securide.back.FunctionalIssue;
import com.securide.securide.back.SimpleFunctionalIssue;
import com.securide.securide.data.Moto;
import com.securide.securide.data.Trajet;
import com.securide.securide.repository.TrajetRepository;

import io.vavr.control.Either;

@Service
public class TrajetService {
	private final TrajetRepository trajetRepository;
	
	@Autowired
	public TrajetService(TrajetRepository trajetRepository) {
		this.trajetRepository = trajetRepository;
	}
	
	public Either<FunctionalIssue, Optional<Trajet>> getTrajetByUuid(UUID uuid){
		if(trajetRepository.getOne(uuid) == null) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(trajetRepository.findById(uuid));
	}
	
	public Either<FunctionalIssue, List<Trajet>> getAllTrajets(){
		List<Trajet> listTrajets = trajetRepository.findAll();
		return Either.right(listTrajets);
	}
	
	public Either<FunctionalIssue, Trajet> createTrajet(Trajet trajet){
		return Either.right(trajetRepository.save(trajet));
	}
}
