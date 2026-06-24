package com.example.srilankaairways.entity;

import jakarta.persistence.*;

@Entity
@Table(name="airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    private String code;
    private String name;
    private String city;

    public Airport(){}

    public Airport(Long airportId,String code,String name,String city){
        this.airportId=airportId;
        this.code=code;
        this.name=name;
        this.city=city;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
