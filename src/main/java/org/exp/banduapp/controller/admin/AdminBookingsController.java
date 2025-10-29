package org.exp.banduapp.controller.admin;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.service.face.AdminBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + ADMIN + BOOKINGS))
@RequiredArgsConstructor
public class AdminBookingsController {

    private final AdminBookingService adminBookingService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BookingRes>> getPlaces() {
        List<BookingRes> bookingResList = adminBookingService.getBookingsResList();
        return new ResponseEntity<>(bookingResList, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingRes> getPlace(@PathVariable Long bookingId) {
        BookingRes bookingRes = adminBookingService.getBookingRes(bookingId);
        return new ResponseEntity<>(bookingRes, HttpStatus.OK);
    }

    @PutMapping("/{bookingId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingRes> acceptAndConfirmBooking(@PathVariable Long bookingId) {
        BookingRes bookingRes = adminBookingService.confirmBooking(bookingId);
        return new ResponseEntity<>(bookingRes, HttpStatus.OK);
    }
}
