package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.RoadMapTaskRes;
import org.exp.banduapp.models.entities.RoadMapTask;
import org.springframework.stereotype.Service;

@Service
public interface RoadMapTaskService {

    RoadMapTaskRes convertToRes(RoadMapTask  roadMapTask);
}
