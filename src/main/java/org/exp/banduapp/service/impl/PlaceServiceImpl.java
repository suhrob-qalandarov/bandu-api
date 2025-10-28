package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.service.face.PlaceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public List<PlaceRes> getAllPlaces() {
        return placeRepository.findAll().stream()
                .map(this::convertToPlaceRes)
                .toList();
    }

    @Override
    public PlaceRes getPlaceById(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow();
        return convertToPlaceRes(place);
    }

    private PlaceRes convertToPlaceRes(Place place) {
        return PlaceRes.builder()
                .id(place.getId())
                .name(place.getName())
                .address(place.getAddress())
                .capacity(place.getCapacity())
                .pricePerHour(place.getPricePerHour())
                .status(place.getStatus().name())
                .build();
    }
}
