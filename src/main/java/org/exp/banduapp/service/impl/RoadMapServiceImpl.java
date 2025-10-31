package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.RoadMapRes;
import org.exp.banduapp.models.dto.response.RoadMapTaskRes;
import org.exp.banduapp.models.entities.RoadMap;
import org.exp.banduapp.repository.RoadMapRepository;
import org.exp.banduapp.service.face.RoadMapService;
import org.exp.banduapp.service.face.RoadMapTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadMapServiceImpl implements RoadMapService {

    private final RoadMapRepository roadMapRepository;
    private final RoadMapTaskService roadMapTaskService;

    @Override
    public List<RoadMapRes> getRoadMaps() {
        return roadMapRepository.findAllByVisibility(true).stream()
                .map(this::convertToRoadMapRes)
                .toList();
    }

    private RoadMapRes convertToRoadMapRes(RoadMap roadMap){
        List<RoadMapTaskRes> taskResList = roadMap.getTasks().stream()
                .map(roadMapTaskService::convertToRes)
                .toList();
        return RoadMapRes.builder()
                .id(roadMap.getId())
                .title(roadMap.getTitle())
                .description(roadMap.getDescription())
                .result(roadMap.getResult())
                .tasks(taskResList)
                .build();
    }
}
