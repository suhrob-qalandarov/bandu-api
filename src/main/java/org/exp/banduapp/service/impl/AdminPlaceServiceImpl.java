package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.admin.PlaceReq;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.service.face.AdminPlaceService;
import org.exp.banduapp.service.face.PlaceService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPlaceServiceImpl implements AdminPlaceService {

    private final PlaceService placeService;
    private final PlaceRepository placeRepository;

    @Override
    public PlaceRes addNewPlace(PlaceReq request) {
        Place builtPlace = Place.builder()
                .name(request.name())
                .description(request.description())
                .address(request.address())
                .capacity(request.capacity())
                .pricePerHour(request.pricePerHour())
                .status(PlaceStatus.ACTIVE)
                .visibility(true)
                .build();
        Place savedPlace = placeRepository.save(builtPlace);
        return placeService.convertToPlaceRes(savedPlace);
    }
}
