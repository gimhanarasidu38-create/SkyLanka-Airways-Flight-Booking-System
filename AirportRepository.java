package com.example.srilankaairways.repository;

import com.example.srilankaairways.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository
        extends JpaRepository<Airport, Long> {
}