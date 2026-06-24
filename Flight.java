package com.example.srilankaairways.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String flightNumber;

    private LocalDateTime departureTime;

    private Integer capacity;

    private Double price;

    @ManyToOne
    @JoinColumn(name="origin_id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name="destination_id")
    private Airport destination;

    public Flight(){}

    public Flight(Long flightId, String flightNumber, LocalDateTime departureTime, Integer capacity, Double price, Airport origin, Airport destination) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
}
