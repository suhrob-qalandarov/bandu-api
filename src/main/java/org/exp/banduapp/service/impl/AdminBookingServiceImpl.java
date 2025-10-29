package org.exp.banduapp.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.models.entities.Booking;
import org.exp.banduapp.repository.BookingRepository;
import org.exp.banduapp.service.face.AdminBookingService;
import org.exp.banduapp.service.face.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminBookingServiceImpl implements AdminBookingService {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @Override
    public List<BookingRes> getBookingsResList() {
        return bookingRepository.findAll().stream()
                .map(bookingService::convertToBookingRes)
                .toList();
    }

    @Override
    public BookingRes getBookingRes(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + bookingId));
        return bookingService.convertToBookingRes(booking);
    }
}
