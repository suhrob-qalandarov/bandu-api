package org.exp.banduapp.service.face;

import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.entities.Place;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaceService {

    List<PlaceRes> getAllPlaces();

    PlaceRes getPlaceById(Long placeId);

    PlaceRes convertToPlaceRes(Place place);
}
