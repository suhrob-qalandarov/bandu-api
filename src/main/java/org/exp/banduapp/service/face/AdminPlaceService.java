package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.request.admin.PlaceReq;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.springframework.stereotype.Service;

@Service
public interface AdminPlaceService {

    PlaceRes addNewPlace(PlaceReq placeReq);
}
