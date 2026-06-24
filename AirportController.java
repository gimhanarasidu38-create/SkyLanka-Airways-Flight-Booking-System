package com.example.srilankaairways.controller;

import com.example.srilankaairways.entity.Airport;
import com.example.srilankaairways.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport savedAirport = airportService.saveAirport(airport);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(
            @PathVariable Long id,
            @RequestBody Airport airport) {
        Airport updatedAirport = airportService.updateAirport(id, airport);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/{id}")   
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
