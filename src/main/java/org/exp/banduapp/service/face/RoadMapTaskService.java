package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.admin.RoadMapTaskReq;
import org.exp.banduapp.models.dto.response.RoadMapTaskRes;
import org.exp.banduapp.models.entities.RoadMapTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoadMapTaskService {

    RoadMapTaskRes convertToRes(RoadMapTask  roadMapTask);

    RoadMapTask convertToEntity(RoadMapTaskReq roadMapTaskReq);

    List<RoadMapTask> convertListToEntityList(List<RoadMapTaskReq> roadMapTaskReqList);

}
