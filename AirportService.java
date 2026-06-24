package com.example.srilankaairways.service;

import com.example.srilankaairways.entity.Airport;
import com.example.srilankaairways.repository.AirportRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found"));
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport airport) {
        Airport existingAirport = getAirportById(id);
        existingAirport.setCode(airport.getCode());
        existingAirport.setName(airport.getName());
        existingAirport.setCity(airport.getCity());
        return airportRepository.save(existingAirport);
    }

    public void deleteAirport(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airport not found");
        }
        airportRepository.deleteById(id);
    }
}
