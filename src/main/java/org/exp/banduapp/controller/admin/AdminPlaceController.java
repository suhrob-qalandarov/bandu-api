package org.exp.banduapp.controller.admin;

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
@RequestMapping((API + V1 + ADMIN + PLACES))
@RequiredArgsConstructor
public class AdminPlaceController {

    private final AdminPlaceService adminPlaceService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PlaceRes>> getPlaces() {
        List<PlaceRes> placeResList = adminPlaceService.getPlaceResList();
        return new ResponseEntity<>(placeResList, HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlaceRes> getPlace(@PathVariable Long placeId) {
        PlaceRes placeRes = adminPlaceService.getPlaceRes(placeId);
        return new ResponseEntity<>(placeRes, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlaceRes> addNewPlace(@Valid @RequestBody PlaceReq placeReq) {
        PlaceRes placeRes = adminPlaceService.addNewPlace(placeReq);
        return new ResponseEntity<>(placeRes, HttpStatus.CREATED);
    }

    @PutMapping("/{placeId}/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlaceRes> updatePlaceStatus(
            @PathVariable Long placeId,
            @PathVariable PlaceStatus status
    ) {
        PlaceRes placeRes = adminPlaceService.updatePlaceStatus(placeId, status);
        return new ResponseEntity<>(placeRes, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{placeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> hidePlace(@PathVariable Long placeId) {
        adminPlaceService.hidePlaceWithVisibility(placeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
