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
import com.securide.securide.data.Boitier;
import com.securide.securide.service.BoitierService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/boitier")
@CrossOrigin
public class BoitierController {
	BoitierService boitierService;
	BoitierController(BoitierService boitierService){
		this.boitierService = boitierService;
	}
	
	@GetMapping
	public ResponseEntity<List<Boitier>> getBoitiers(){
		return ResponseEntityBuilder.matchEitherOnGet(boitierService.getBoitiers());
	}
	
	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get a boitier by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of boitier") })
	public ResponseEntity<Boitier> getBoitier(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(boitierService.getBoitierByUuid(uuid));
	}
	
	@PostMapping
	@ApiOperation(value = "Create a boitier", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create boitier") })
	public ResponseEntity<Boitier> createBoitier(@Valid @RequestBody final Boitier boitier) {
		return ResponseEntityBuilder.matchEitherOnPost(boitierService.createBoitier(boitier));
	}
}
