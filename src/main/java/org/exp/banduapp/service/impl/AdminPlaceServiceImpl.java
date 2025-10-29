package org.exp.banduapp.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.admin.PlaceReq;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.models.enums.PlaceStatus;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.service.face.AdminPlaceService;
import org.exp.banduapp.service.face.PlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public PlaceRes updatePlaceStatus(Long placeId, PlaceStatus status) {
        List<Place> result = placeRepository.updateStatusAndReturnEntity(
                placeId,
                status.name()
        );

        if (result.isEmpty()) {
            throw new EntityNotFoundException("Place not found with id: " + placeId);
        }

        Place statusUpdatedPlace = result.getFirst();
        return placeService.convertToPlaceRes(statusUpdatedPlace);
    }

    @Override
    @Transactional
    public void hidePlaceWithVisibility(Long placeId) {
        int updated = placeRepository.hidePlaceById(placeId);

        if (updated == 0) {
            throw new EntityNotFoundException("Place not found with id: " + placeId);
        }
    }
}
