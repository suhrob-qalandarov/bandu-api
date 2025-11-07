/*
package org.exp.banduapp.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.BookingRes;
import org.exp.banduapp.service.face.AdminBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping(API + V1 + ADMIN + BOOKINGS)
@RequiredArgsConstructor
@Tag(name = "Admin: Bronlar", description = "Admin uchun bronlarni boshqarish")
public class AdminBookingsController {

    private final AdminBookingService adminBookingService;

    @Operation(summary = "Barcha bronlar", description = "Tizimdagi barcha bronlar (status va visibility bo'yicha)")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<BookingRes>> getBookings() {
        return ResponseEntity.ok(adminBookingService.getBookingsResList());
    }

    @Operation(summary = "Bron haqida batafsil", description = "Bitta bronning to'liq ma'lumotlari")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingRes> getBooking(
            @PathVariable @Parameter(description = "Bron ID si") Long bookingId
    ) {
        return ResponseEntity.ok(adminBookingService.getBookingRes(bookingId));
    }

    @Operation(summary = "Bronni tasdiqlash", description = "Status: PENDING → CONFIRMED")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bron tasdiqlandi"),
            @ApiResponse(responseCode = "400", description = "Bron topilmadi yoki allaqachon tasdiqlangan")
    })
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingRes> confirmBooking(
            @PathVariable @Parameter(description = "Bron ID si") Long bookingId
    ) {
        return ResponseEntity.ok(adminBookingService.confirmBooking(bookingId));
    }
}
*/
