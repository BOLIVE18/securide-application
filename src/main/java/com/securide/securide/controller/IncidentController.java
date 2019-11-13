package com.securide.securide.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securide.securide.back.ResponseEntityBuilder;
import com.securide.securide.data.Incident;
import com.securide.securide.service.IncidentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/incident")
@CrossOrigin
public class IncidentController {
	private IncidentService incidentService;

	public IncidentController(IncidentService incidentService) {
		this.incidentService = incidentService;
	}

	@GetMapping
	@ApiOperation(value = "Get all incident")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Succesful retrieval of incidents")
	})
	public ResponseEntity<List<Incident>> getAllIncidents(){
		return ResponseEntityBuilder.matchEitherOnGet(incidentService.getAllIncidents());
	}
	
	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get an incident by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of incident") })
	public ResponseEntity<Incident> getIncident(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(incidentService.getIncidentByUuid(uuid));
	}

	@PostMapping
	@ApiOperation(value = "Create an incident", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create incident") })
	public ResponseEntity<Incident> createMoto(@Valid @RequestBody final Incident incident) {
		return ResponseEntityBuilder.matchEitherOnPost(incidentService.createIncident(incident));
	}
}
