package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.RoadMapTaskRes;
import org.exp.banduapp.models.entities.RoadMapTask;
import org.exp.banduapp.service.face.RoadMapTaskService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoadMapTaskServiceImpl implements RoadMapTaskService {

    @Override
    public RoadMapTaskRes convertToRes(RoadMapTask roadMapTask) {
        return RoadMapTaskRes.builder()
                .id(roadMapTask.getId())
                .task(roadMapTask.getTask())
                .completed(roadMapTask.isCompleted())
                .build();
    }
}
