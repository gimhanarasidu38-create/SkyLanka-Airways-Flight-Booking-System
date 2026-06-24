package com.example.srilankaairways.repository;

import com.example.srilankaairways.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking,Long> {

    List<Booking> findByBookingStatus(String bookingStatus);
}