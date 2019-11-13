package com.securide.securide.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.securide.securide.back.FunctionalIssue;
import com.securide.securide.back.SimpleFunctionalIssue;
import com.securide.securide.data.Boitier;
import com.securide.securide.repository.BoitierRepository;

import io.vavr.control.Either;

@Service
public class BoitierService {
	BoitierRepository boitierRepository;
	
	BoitierService(BoitierRepository boitierRepository){
		this.boitierRepository = boitierRepository;
	}
	
	public Either<FunctionalIssue, List<Boitier>> getBoitiers(){
		return Either.right(boitierRepository.findAll());
	}
	
	public Either<FunctionalIssue,Optional<Boitier>> getBoitierByUuid(UUID uuid) {
		Optional<Boitier> optionalBoitier = boitierRepository.findById(uuid);
		if(!optionalBoitier.isPresent()) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "BOITIER_NOT_FOUND"));
		}
		return Either.right(optionalBoitier);
	}
	
	public Either<FunctionalIssue, Boitier> createBoitier(Boitier boitier){
		return Either.right(boitierRepository.save(boitier));
	}
}
