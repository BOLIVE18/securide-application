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
import com.securide.securide.repository.MotoRepository;

import io.vavr.control.Either;

@Service
public class MotoService {
	private final MotoRepository motoRepository;
	
	@Autowired
	public MotoService(MotoRepository motoRepository) {
		this.motoRepository = motoRepository;
	}
	
	public Either<FunctionalIssue, Optional<Moto>> getMotoByUuid(UUID uuid){
		if(motoRepository.getOne(uuid) == null) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(motoRepository.findById(uuid));
	}
	
	public Either<FunctionalIssue, List<Moto>> getAllMoto(){
		List<Moto> listMoto = motoRepository.findAll();
		return Either.right(listMoto);
	}
	
	public Either<FunctionalIssue, Moto> createMoto(Moto moto){
		return Either.right(motoRepository.save(moto));
	}
}
