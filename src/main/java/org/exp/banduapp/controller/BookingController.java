package org.exp.banduapp.controller;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.service.face.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + BOOKINGS))
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingRes>> getClientBookings(@AuthenticationPrincipal User user) {
        List<BookingRes> bookingResList = bookingService.getUserBookingsRes(user);
        return new ResponseEntity<>(bookingResList, HttpStatus.OK);
    }
}
