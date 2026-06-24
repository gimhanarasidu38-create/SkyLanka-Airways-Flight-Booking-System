package com.example.srilankaairways.repository;

import com.example.srilankaairways.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FlightRepository
        extends JpaRepository<Flight,Long> {

    Page<Flight> findByFlightNumberContainingIgnoreCase(
            String flightNumber,
            Pageable pageable);

    List<Flight> findByOrigin_CodeAndDestination_Code(
            String originCode,
            String destinationCode);
}