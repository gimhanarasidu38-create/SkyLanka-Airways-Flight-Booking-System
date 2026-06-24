package com.example.srilankaairways.service;

import com.example.srilankaairways.entity.Booking;
import com.example.srilankaairways.repository.BookingRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking booking) {
        Booking existingBooking = getBookingById(id);
        existingBooking.setPassengerName(booking.getPassengerName());
        existingBooking.setPassengerEmail(booking.getPassengerEmail());
        existingBooking.setSeatNumber(booking.getSeatNumber());
        existingBooking.setBookingStatus(booking.getBookingStatus());
        existingBooking.setFlight(booking.getFlight());
        existingBooking.setUser(booking.getUser());
        return bookingRepository.save(existingBooking);
    }

    public Booking patchBooking(Long id, Booking booking) {
        Booking existingBooking = getBookingById(id);
        if (booking.getPassengerName() != null) {
            existingBooking.setPassengerName(booking.getPassengerName());
        }
        if (booking.getPassengerEmail() != null) {
            existingBooking.setPassengerEmail(booking.getPassengerEmail());
        }
        if (booking.getSeatNumber() != null) {
            existingBooking.setSeatNumber(booking.getSeatNumber());
        }
        if (booking.getBookingStatus() != null) {
            existingBooking.setBookingStatus(booking.getBookingStatus());
        }
        if (booking.getFlight() != null) {
            existingBooking.setFlight(booking.getFlight());
        }
        if (booking.getUser() != null) {
            existingBooking.setUser(booking.getUser());
        }
        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }
        bookingRepository.deleteById(id);
    }

    public List<Booking> findByStatus(String status) {
        return bookingRepository.findByBookingStatus(status);
    }
}
