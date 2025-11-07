/*
package org.exp.banduapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.BookingReq;
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
@RequestMapping(API + V1 + BOOKINGS)
@RequiredArgsConstructor
@Tag(name = "Foydalanuvchi bronlash", description = "Foydalanuvchilar o'z bronlarini boshqarishi")
public class BookingController {

    private final BookingService bookingService;

    @Operation(
            summary = "Foydalanuvchi bronlar ro'yxati",
            description = "Login qilgan foydalanuvchining faol (visibility=true) bronlarini qaytaradi"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bronlar muvaffaqiyatli qaytarildi",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookingRes.class)))),
            @ApiResponse(responseCode = "401", description = "Foydalanuvchi autentifikatsiya qilinmagan")
    })
    @GetMapping
    public ResponseEntity<List<BookingRes>> getClientBookings(@AuthenticationPrincipal User user) {
        List<BookingRes> bookingResList = bookingService.getUserBookingsRes(user);
        return ResponseEntity.ok(bookingResList);
    }

    @Operation(
            summary = "Yangi bron yaratish",
            description = "Foydalanuvchi joyni belgilangan vaqtda bron qiladi. Vaqt band bo'lsa xato qaytadi"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bron muvaffaqiyatli yaratildi",
                    content = @Content(schema = @Schema(implementation = BookingRes.class))),
            @ApiResponse(responseCode = "400", description = "Noto'g'ri ma'lumotlar yoki vaqt band"),
            @ApiResponse(responseCode = "401", description = "Autentifikatsiya talab qilinadi")
    })
    @PostMapping
    public ResponseEntity<BookingRes> clientBookingPlace(
            @Valid @RequestBody @Parameter(description = "Bronlash uchun ma'lumotlar") BookingReq bookingReq,
            @AuthenticationPrincipal User user
    ) {
        BookingRes bookingRes = bookingService.newBooking(bookingReq, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingRes);
    }

    @Operation(
            summary = "Bronni bekor qilish",
            description = "Foydalanuvchi faqat o'z bronini bekor qila oladi. Status: CANCELLED"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bron bekor qilindi",
                    content = @Content(schema = @Schema(implementation = BookingRes.class))),
            @ApiResponse(responseCode = "400", description = "Bron topilmadi yoki bekor qilib bo'lmaydi"),
            @ApiResponse(responseCode = "401", description = "Autentifikatsiya talab qilinadi")
    })
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingRes> clientCancelledBooking(
            @PathVariable @Parameter(description = "Bron ID si") Long bookingId,
            @AuthenticationPrincipal User user
    ) {
        BookingRes bookingRes = bookingService.cancelBooking(bookingId, user);
        return ResponseEntity.ok(bookingRes);
    }
}
*/
