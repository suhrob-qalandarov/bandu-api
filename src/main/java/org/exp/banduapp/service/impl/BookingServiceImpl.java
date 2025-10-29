package org.exp.banduapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.BookingReq;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.models.entities.Booking;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.models.enums.BookingStatus;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.repository.BookingRepository;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.service.face.BookingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PlaceRepository placeRepository;

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

    @Override
    @Transactional
    public BookingRes newBooking(BookingReq bookingReq, User user) {
        Place place = placeRepository.findByIdAndVisibilityTrue(bookingReq.placeId())
                .orElseThrow(() -> new IllegalArgumentException("Place not found or not visible"));

        if (place.getStatus() != PlaceStatus.ACTIVE) {
            throw new IllegalStateException("Place is not active for booking");
        }

        LocalDateTime start = bookingReq.startTime();
        LocalDateTime end = bookingReq.endTime();

        if (end.isBefore(start.plusHours(1))) {
            throw new IllegalArgumentException("Booking must be at least 1 hour");
        }

        boolean hasOverlap = bookingRepository.existsByPlaceAndStatusInAndTimeOverlap(
                place,
                Set.of(BookingStatus.PENDING, BookingStatus.CONFIRMED),
                start,
                end
        );

        if (hasOverlap) {
            throw new RuntimeException("Selected time slot is already booked");
        }

        long hours = ChronoUnit.HOURS.between(start, end);
        long extraMinutes = ChronoUnit.MINUTES.between(start.plusHours(hours), end);
        if (extraMinutes > 0) {
            hours += (extraMinutes >= 30) ? 1 : 0;
        }

        BigDecimal totalPrice = place.getPricePerHour().multiply(BigDecimal.valueOf(hours));

        LocalDateTime expiresAt = start.minusMinutes(15);

        Booking booking = Booking.builder()
                .user(user)
                .place(place)
                .startTime(start)
                .endTime(end)
                .totalPrice(totalPrice)
                .status(BookingStatus.PENDING)
                .expiresAt(expiresAt)
                .note(bookingReq.note())
                .visibility(true)
                .build();

        booking.setStartTime(start);
        booking = bookingRepository.save(booking);

        return convertToBookingRes(booking);
    }

    @Override
    public BookingRes cancelBooking(Long bookingId, User user) {
        List<Booking> result = bookingRepository.cancelAndReturnBooking(
                bookingId,
                user.getId()
        );

        if (result.isEmpty()) {
            throw new IllegalStateException("Bron topilmadi yoki bekor qilib bo'lmaydi");
        }

        Booking cancelledBooking = result.getFirst();
        return convertToBookingRes(cancelledBooking);
    }

    private BookingRes convertToBookingRes(Booking booking) {
        return BookingRes.builder()
                .id(booking.getId())
                .placeName(booking.getPlace().getName())
                .placeAddress(booking.getPlace().getAddress())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .pricePerHour(booking.getPlace().getPricePerHour())
                .totalPrice(booking.getTotalPrice())
                .status(booking.getStatus().name())
                .note(booking.getNote())
                .expiresAt(booking.getExpiresAt())
                .build();
    }
}
