package com.example.srilankaairways.entity;

import jakarta.persistence.*;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private String passengerName;

    private String passengerEmail;

    private String seatNumber;

    private String bookingStatus;

    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Booking(){}

    public Booking(Long bookingId, String passengerName, String passengerEmail, String seatNumber, String bookingStatus, Flight flight, User user) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.seatNumber = seatNumber;
        this.bookingStatus = bookingStatus;
        this.flight = flight;
        this.user = user;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
