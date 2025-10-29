package org.exp.banduapp.controller;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.service.face.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + PLACES))
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<PlaceRes>> getPlaces() {
        List<PlaceRes> placesResList = placeService.getAllPlaces();
        return new ResponseEntity<>(placesResList, HttpStatus.OK);
    }

    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceRes> getPlaceById(@PathVariable Long placeId) {
        PlaceRes placeRes = placeService.getPlaceById(placeId);
        return new ResponseEntity<>(placeRes, HttpStatus.OK);
    }
}
