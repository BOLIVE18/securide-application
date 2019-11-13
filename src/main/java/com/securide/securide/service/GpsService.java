package com.securide.securide.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.securide.securide.back.FunctionalIssue;
import com.securide.securide.back.SimpleFunctionalIssue;
import com.securide.securide.data.Gps;
import com.securide.securide.data.Moto;
import com.securide.securide.repository.GpsRepository;
import com.securide.securide.repository.MotoRepository;

import io.vavr.control.Either;

@Service
public class GpsService {
	private final GpsRepository gpsRepository;
	
	@Autowired
	public GpsService(GpsRepository gpsRepository) {
		this.gpsRepository = gpsRepository;
	}
	
	public Either<FunctionalIssue, Optional<Gps>> getGpsByUuid(UUID uuid){
		if(gpsRepository.getOne(uuid) == null) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(gpsRepository.findById(uuid));
	}
	
	public Either<FunctionalIssue, List<Gps>> getAllGps(){
		List<Gps> listGps = gpsRepository.findAll();
		return Either.right(listGps);
	}
	
	public Either<FunctionalIssue, Gps> createGps(Gps gps){
		return Either.right(gpsRepository.save(gps));
	}
}
