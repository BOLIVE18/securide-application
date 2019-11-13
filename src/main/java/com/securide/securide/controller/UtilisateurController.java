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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.securide.securide.back.ResponseEntityBuilder;
import com.securide.securide.data.Utilisateur;
import com.securide.securide.service.UtilisateurService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/utilisateur")
@CrossOrigin
public class UtilisateurController {
	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@GetMapping
	@ApiOperation(value = "Get all utilisateurs")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of utilisateurs") })
	public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
		return ResponseEntityBuilder.matchEitherOnGet(utilisateurService.getAllUtilisateur());
	}

	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get an utilisateur by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of utilisateur") })
	public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(utilisateurService.getUtilisateurByUuid(uuid));
	}

	@GetMapping("/email")
	public ResponseEntity<Utilisateur> getUtilisateurByEmailAndMdp(@RequestParam(value = "email") final String email,
			@RequestParam(value = "mdp") final String mdp) {
		return ResponseEntityBuilder.matchEitherOnGet(utilisateurService.getUtilisateurByMailAndMdp(email, mdp));
	}

	@PostMapping
	@ApiOperation(value = "Create an utilisateur", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create utilisateur") })
	public ResponseEntity<Utilisateur> createUtilisateur(@Valid @RequestBody final Utilisateur utilisateur) {
		return ResponseEntityBuilder.matchEitherOnPost(utilisateurService.createUtilisateur(utilisateur));
	}
}
