package org.exp.banduapp.controller.admin;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.admin.RoadMapReq;
import org.exp.banduapp.models.dto.response.RoadMapRes;
import org.exp.banduapp.service.face.RoadMapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + ADMIN + ROADMAP))
@RequiredArgsConstructor
public class AdminRoadMapController {

    private final RoadMapService roadMapService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoadMapRes>> getRoadMaps() {
        List<RoadMapRes> roadMaps = roadMapService.getAdminRoadMapResList();
        return new ResponseEntity<>(roadMaps, HttpStatus.OK);
    }

    @GetMapping("/{roadmapId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoadMapRes> getRoadMapById(@PathVariable Long roadmapId) {
        RoadMapRes roadMapRes = roadMapService.getAdminRoadMapRes(roadmapId);
        return new ResponseEntity<>(roadMapRes, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoadMapRes> createNewRoadMap(@RequestBody RoadMapReq roadMapReq) {
        RoadMapRes roadMapRes = roadMapService.createAndReturnRes(roadMapReq);
        return new ResponseEntity<>(roadMapRes, HttpStatus.CREATED);
    }

    @PutMapping("/{roadmapId}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> toggleRoadMapVisibility(@PathVariable Long roadmapId) {
        roadMapService.toggleRoadMap(roadmapId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
