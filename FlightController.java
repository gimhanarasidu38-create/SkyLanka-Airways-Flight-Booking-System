package com.example.srilankaairways.controller;

import com.example.srilankaairways.entity.Flight;
import com.example.srilankaairways.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<Page<Flight>> getAll(
            @PageableDefault(size = 5, sort = "flightId") Pageable pageable) {
        return ResponseEntity.ok(flightService.getAllFlights(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PostMapping
    public ResponseEntity<Flight> add(@RequestBody Flight flight) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.saveFlight(flight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> update(@PathVariable Long id,
                         @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Flight> patch(@PathVariable Long id,
                        @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(flightService.patchFlight(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Flight>> search(
            @RequestParam String flightNumber,
            Pageable pageable) {
        return ResponseEntity.ok(flightService.searchByFlightNumber(flightNumber, pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Flight>> filterFlights(
            @RequestParam String origin,
            @RequestParam String destination) {
        return ResponseEntity.ok(flightService.filterFlights(origin, destination));
    }
}
