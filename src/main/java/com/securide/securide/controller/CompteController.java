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
import com.securide.securide.data.Compte;
import com.securide.securide.service.CompteService;
import com.securide.securide.service.UtilisateurService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/compte")
@CrossOrigin
public class CompteController {
	private CompteService compteService;

	public CompteController(CompteService compteService) {
		this.compteService = compteService;
	}

	@GetMapping
	@ApiOperation(value = "Get all comptes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Succesful retrieval of comptes")
	})
	public ResponseEntity<List<Compte>> getAllComptes(){
		return ResponseEntityBuilder.matchEitherOnGet(compteService.getAllComptes());
	}
	
	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get an compte by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of compte") })
	public ResponseEntity<Compte> getUtilisateur(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(compteService.getCompteByUuid(uuid));
	}

	@PostMapping
	@ApiOperation(value = "Create a compte", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create utilisateur") })
	public ResponseEntity<Compte> createUtilisateur(@Valid @RequestBody final Compte compte) {
		return ResponseEntityBuilder.matchEitherOnPost(compteService.createCompte(compte));
	}
}
