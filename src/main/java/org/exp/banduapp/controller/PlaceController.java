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
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.service.face.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping(API + V1 + PLACES)
@RequiredArgsConstructor
@Tag(name = "Joylar (umumiy)", description = "Barcha foydalanuvchilar uchun ochiq joy ma'lumotlari")
public class PlaceController {

    private final PlaceService placeService;

    @Operation(summary = "Barcha faol joylar", description = "Status: ACTIVE bo'lgan joylarni qaytaradi")
    @ApiResponse(responseCode = "200", description = "Joylar ro'yxati",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlaceRes.class))))
    @GetMapping
    public ResponseEntity<List<PlaceRes>> getPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @Operation(summary = "Joy haqida batafsil", description = "Bitta joyning to'liq ma'lumotlari")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Joy topildi",
                    content = @Content(schema = @Schema(implementation = PlaceRes.class))),
            @ApiResponse(responseCode = "400", description = "Joy topilmadi")
    })
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceRes> getPlaceById(
            @PathVariable @Parameter(description = "Joy ID si") Long placeId
    ) {
        return ResponseEntity.ok(placeService.getPlaceById(placeId));
    }
}
*/
