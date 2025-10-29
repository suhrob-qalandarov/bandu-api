package org.exp.banduapp.controller.admin;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.admin.PlaceReq;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.service.face.AdminPlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + ADMIN + PLACES))
@RequiredArgsConstructor
public class AdminPlaceController {

    private final AdminPlaceService adminPlaceService;

    @PostMapping
    public ResponseEntity<PlaceRes> addNewPlace(@RequestBody PlaceReq placeReq) {
        PlaceRes placeRes = adminPlaceService.addNewPlace(placeReq);
        return new ResponseEntity<>(placeRes, HttpStatus.CREATED);
    }

    @PutMapping("/{placeId}/status/{status}")
    public ResponseEntity<PlaceRes> updatePlaceStatus(
            @PathVariable Long placeId,
            @PathVariable PlaceStatus status
    ) {
        PlaceRes placeRes = adminPlaceService.updatePlaceStatus(placeId, status);
        return new ResponseEntity<>(placeRes, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{placeId}")
    public ResponseEntity<Void> hidePlace(@PathVariable Long placeId) {
        adminPlaceService.hidePlaceWithVisibility(placeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
