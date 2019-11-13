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
import com.securide.securide.service.MotoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/moto")
@CrossOrigin
public class MotoController {
	private MotoService motoService;

	public MotoController(MotoService motoService) {
		this.motoService = motoService;
	}

	@GetMapping
	@ApiOperation(value = "Get all moto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Succesful retrieval of moto")
	})
	public ResponseEntity<List<Moto>> getAllMoto(){
		return ResponseEntityBuilder.matchEitherOnGet(motoService.getAllMoto());
	}
	
	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get a moto by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of moto") })
	public ResponseEntity<Moto> getMoto(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(motoService.getMotoByUuid(uuid));
	}

	@PostMapping
	@ApiOperation(value = "Create a moto", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create moto") })
	public ResponseEntity<Moto> createMoto(@Valid @RequestBody final Moto moto) {
		return ResponseEntityBuilder.matchEitherOnPost(motoService.createMoto(moto));
	}
}
