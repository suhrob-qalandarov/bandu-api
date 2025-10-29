package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.models.entities.Booking;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.repository.BookingRepository;
import org.exp.banduapp.service.face.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<BookingRes> getUserBookingsRes(User user) {
        if (user == null || user.getId() == null) {
            return List.of();
        }

        return bookingRepository
                .findByUserAndVisibilityTrueOrderByCreatedAtDesc(user)
                .stream()
                .map(this::convertToBookingRes)
                .toList();
    }

    private BookingRes convertToBookingRes(Booking booking) {
        return BookingRes.builder()
                .id(booking.getId())
                .placeName(booking.getPlace().getName())
                .placeAddress(booking.getPlace().getAddress())
                .startTime(booking.getCreatedAt())
                .endTime(booking.getEndTime())
                .pricePerHour(booking.getPlace().getPricePerHour())
                .totalPrice(booking.getTotalPrice())
                .status(booking.getStatus().name())
                .note(booking.getNote())
                .expiresAt(booking.getExpiresAt())
                .build();
    }
}
