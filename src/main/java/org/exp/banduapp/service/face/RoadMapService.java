package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.admin.RoadMapReq;
import org.exp.banduapp.models.dto.response.RoadMapRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoadMapService {

    List<RoadMapRes> getRoadMaps();

    List<RoadMapRes> getAdminRoadMaps();

    RoadMapRes getAdminRoadMapRes(Long roadmapId);

    RoadMapRes createAndReturnRes(RoadMapReq roadMapReq);
}
