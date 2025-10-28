package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.PlaceRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceService {

    List<PlaceRes> getAllPlaces();

    PlaceRes getPlaceById(Long placeId);
}
