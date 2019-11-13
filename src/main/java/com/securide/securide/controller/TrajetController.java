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
import com.securide.securide.data.Moto;
import com.securide.securide.data.Trajet;
import com.securide.securide.service.MotoService;
import com.securide.securide.service.TrajetService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/trajets")
@CrossOrigin
public class TrajetController {
	private TrajetService trajetService;

	public TrajetController(TrajetService trajetService) {
		this.trajetService = trajetService;
	}

	@GetMapping
	@ApiOperation(value = "Get all trajets")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Succesful retrieval of trajet")
	})
	public ResponseEntity<List<Trajet>> getAllTrajets(){
		return ResponseEntityBuilder.matchEitherOnGet(trajetService.getAllTrajets());
	}
	
	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get a moto by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of moto") })
	public ResponseEntity<Trajet> getTrajet(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(trajetService.getTrajetByUuid(uuid));
	}

	@PostMapping
	@ApiOperation(value = "Create a trajet", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create trajet") })
	public ResponseEntity<Trajet> createTrajet(@Valid @RequestBody final Trajet trajet) {
		return ResponseEntityBuilder.matchEitherOnPost(trajetService.createTrajet(trajet));
	}
}
