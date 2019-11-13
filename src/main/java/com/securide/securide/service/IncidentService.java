package com.securide.securide.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.securide.securide.back.FunctionalIssue;
import com.securide.securide.back.SimpleFunctionalIssue;
import com.securide.securide.data.Incident;
import com.securide.securide.data.Moto;
import com.securide.securide.repository.IncidentRepository;

import io.vavr.control.Either;

@Service
public class IncidentService {
	private final IncidentRepository incidentRepository;
	
	@Autowired
	public IncidentService(IncidentRepository incidentRepository) {
		this.incidentRepository = incidentRepository;
	}
	
	public Either<FunctionalIssue, Optional<Incident>> getIncidentByUuid(UUID uuid){
		if(incidentRepository.getOne(uuid) == null) {
			return Either.left(new SimpleFunctionalIssue(HttpStatus.BAD_REQUEST, "ACTOR_NOT_FOUND"));
		}
		return Either.right(incidentRepository.findById(uuid));
	}
	
	public Either<FunctionalIssue, List<Incident>> getAllIncidents(){
		List<Incident> listIncidents = incidentRepository.findAll();
		return Either.right(listIncidents);
	}
	
	public Either<FunctionalIssue, Incident> createIncident(Incident incident){
		return Either.right(incidentRepository.save(incident));
	}
}
