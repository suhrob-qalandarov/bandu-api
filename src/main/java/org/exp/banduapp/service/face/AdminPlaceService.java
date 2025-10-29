package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.admin.PlaceReq;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminPlaceService {

    PlaceRes getPlaceRes(Long placeId);

    PlaceRes addNewPlace(PlaceReq placeReq);

    PlaceRes updatePlaceStatus(Long placeId, PlaceStatus status);

    void hidePlaceWithVisibility(Long placeId);

    List<PlaceRes> getPlaceResList();
}
