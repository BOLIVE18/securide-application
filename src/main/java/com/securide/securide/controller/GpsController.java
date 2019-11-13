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
import com.securide.securide.data.Gps;
import com.securide.securide.service.GpsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/gps")
@CrossOrigin
public class GpsController {
	private GpsService gpsService;

	public GpsController(GpsService gpsService) {
		this.gpsService = gpsService;
	}

	@GetMapping
	@ApiOperation(value = "Get all gps")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of gps") })
	public ResponseEntity<List<Gps>> getAllGps() {
		return ResponseEntityBuilder.matchEitherOnGet(gpsService.getAllGps());
	}

	@GetMapping("/{uuid}")
	@ApiOperation(value = "Get a gps by uuid", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesful retrieval of moto") })
	public ResponseEntity<Gps> getMoto(@PathVariable(value = "uuid") final UUID uuid) {
		return ResponseEntityBuilder.matchEitherOnGet(gpsService.getGpsByUuid(uuid));
	}

	@PostMapping
	@ApiOperation(value = "Create a gps", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully create moto") })
	public ResponseEntity<Gps> createGps(@Valid @RequestBody final Gps gps) {
		return ResponseEntityBuilder.matchEitherOnPost(gpsService.createGps(gps));
	}
}
