/*
package org.exp.banduapp.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.admin.PlaceReq;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.service.face.AdminPlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping(API + V1 + ADMIN + PLACES)
@RequiredArgsConstructor
@Tag(name = "Admin: Joylar", description = "Joylarni boshqarish (qo'shish, o'zgartirish, yashirish)")
public class AdminPlaceController {

    private final AdminPlaceService adminPlaceService;

    @Operation(summary = "Admin uchun barcha joylar", description = "Status va visibility bo'yicha filter yo'q")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PlaceRes>> getPlaces() {
        return ResponseEntity.ok(adminPlaceService.getPlaceResList());
    }

    @Operation(summary = "Joy ma'lumotlari (admin)", description = "Hatto yashirin joylar ham ko'rinadi")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceRes> getPlace(
            @PathVariable @Parameter(description = "Joy ID si") Long placeId
    ) {
        return ResponseEntity.ok(adminPlaceService.getPlaceRes(placeId));
    }

    @Operation(summary = "Yangi joy qo'shish", description = "Status: ACTIVE avtomatik")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "201", description = "Joy yaratildi")
    @PostMapping
    public ResponseEntity<PlaceRes> addNewPlace(@Valid @RequestBody PlaceReq placeReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminPlaceService.addNewPlace(placeReq));
    }

    @Operation(summary = "Joy statusini o'zgartirish", description = "Masalan: ACTIVE → MAINTENANCE")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{placeId}/status/{status}")
    public ResponseEntity<PlaceRes> updatePlaceStatus(
            @PathVariable @Parameter(description = "Joy ID si") Long placeId,
            @PathVariable @Parameter(description = "Yangi status: ACTIVE, MAINTENANCE, CLOSED") PlaceStatus status
    ) {
        return ResponseEntity.accepted().body(adminPlaceService.updatePlaceStatus(placeId, status));
    }

    @Operation(summary = "Joyni yashirish", description = "Soft delete: visibility = false")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "204", description = "Joy yashirildi")
    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> hidePlace(
            @PathVariable @Parameter(description = "Joy ID si") Long placeId
    ) {
        adminPlaceService.hidePlaceWithVisibility(placeId);
        return ResponseEntity.noContent().build();
    }
}*/
