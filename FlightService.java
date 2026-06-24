package com.example.srilankaairways.service;

import com.example.srilankaairways.entity.Flight;
import com.example.srilankaairways.repository.FlightRepository;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Page<Flight> getAllFlights(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flight) {
        Flight existingFlight = getFlightById(id);
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setCapacity(flight.getCapacity());
        existingFlight.setPrice(flight.getPrice());
        existingFlight.setOrigin(flight.getOrigin());
        existingFlight.setDestination(flight.getDestination());
        return flightRepository.save(existingFlight);
    }

    public Flight patchFlight(Long id, Map<String, Object> updates) {
        Flight flight = getFlightById(id);
        if (updates.containsKey("price")) {
            flight.setPrice(Double.parseDouble(updates.get("price").toString()));
        }
        if (updates.containsKey("capacity")) {
            flight.setCapacity(Integer.parseInt(updates.get("capacity").toString()));
        }
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found");
        }
        flightRepository.deleteById(id);
    }

    public Page<Flight> searchByFlightNumber(String flightNumber, Pageable pageable) {
        return flightRepository.findByFlightNumberContainingIgnoreCase(flightNumber, pageable);
    }

    public List<Flight> filterFlights(String origin, String destination) {
        return flightRepository.findByOrigin_CodeAndDestination_Code(origin, destination);
    }
}
