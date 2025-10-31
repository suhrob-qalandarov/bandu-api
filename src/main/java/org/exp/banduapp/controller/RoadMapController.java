package org.exp.banduapp.controller;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.RoadMapRes;
import org.exp.banduapp.service.face.RoadMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + ROADMAP))
@RequiredArgsConstructor
public class RoadMapController {

    private final RoadMapService roadMapService;

    @GetMapping
    public ResponseEntity<List<RoadMapRes>> getRoadMap() {
        List<RoadMapRes> roadMaps = roadMapService.getRoadMaps();
        return ResponseEntity.ok(roadMaps);
    }

}
