package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.RoadMapRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoadMapService {

    List<RoadMapRes> getRoadMaps();
}
