package org.exp.banduapp.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.admin.RoadMapReq;
import org.exp.banduapp.models.dto.request.admin.RoadMapTaskReq;
import org.exp.banduapp.models.dto.response.RoadMapRes;
import org.exp.banduapp.models.dto.response.RoadMapTaskRes;
import org.exp.banduapp.models.entities.RoadMap;
import org.exp.banduapp.models.entities.RoadMapTask;
import org.exp.banduapp.repository.RoadMapRepository;
import org.exp.banduapp.service.face.RoadMapService;
import org.exp.banduapp.service.face.RoadMapTaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<RoadMapRes> getAdminRoadMapResList() {
        return roadMapRepository.findAll().stream()
                .map(this::convertToRoadMapRes)
                .toList();
    }

    @Override
    public RoadMapRes getAdminRoadMapRes(Long roadmapId) {
        RoadMap roadMap = roadMapRepository.findById(roadmapId)
                .orElseThrow(() -> new EntityNotFoundException("Roadmap not found with id: " + roadmapId));
        return convertToRoadMapRes(roadMap);
    }

    @Override
    public RoadMapRes createAndReturnRes(RoadMapReq roadMapReq) {
        RoadMap convertedRoadMap = convertToEntity(roadMapReq);
        RoadMap savedRoadMap = roadMapRepository.save(convertedRoadMap);
        return convertToRoadMapRes(savedRoadMap);
    }

    @Override
    public void toggleRoadMap(Long roadmapId) {
        RoadMap roadMap = roadMapRepository.findById(roadmapId)
                .orElseThrow(() -> new EntityNotFoundException("Roadmap not found with id: " + roadmapId));
        roadMap.setVisibility(!roadMap.getVisibility());
        roadMapRepository.save(roadMap);
    }

    private RoadMapRes convertToRoadMapRes(RoadMap roadMap){
        List<RoadMapTaskRes> taskResList = new ArrayList<>();

        if (!roadMap.getTasks().isEmpty()) {
            List<RoadMapTaskRes> roadMapTaskResList = roadMap.getTasks().stream()
                    .map(roadMapTaskService::convertToRes)
                    .toList();
            taskResList.addAll(roadMapTaskResList);
        }

        return RoadMapRes.builder()
                .id(roadMap.getId())
                .title(roadMap.getTitle())
                .description(roadMap.getDescription())
                .result(roadMap.getResult())
                .tasks(taskResList)
                .build();
    }

    private RoadMap convertToEntity(RoadMapReq roadMapReq) {
        List<RoadMapTaskReq> roadMapReqList = roadMapReq.roadMapTasksRequestList();
        List<RoadMapTask> roadMapTasks = new ArrayList<>();

        if (!roadMapReqList.isEmpty()) {
            List<RoadMapTask> convertedRoadMapTaskList = roadMapTaskService.convertListToEntityList(roadMapReqList);
            roadMapTasks.addAll(convertedRoadMapTaskList);
        }

        return RoadMap.builder()
                .title(roadMapReq.title())
                .description(roadMapReq.description())
                .result(roadMapReq.result())
                .tasks(roadMapTasks)
                .visibility(roadMapReq.visibility())
                .build();
    }
}
