package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.PlaceRes;
import org.exp.banduapp.models.dto.response.TimeSlot;
import org.exp.banduapp.models.entities.Place;
import org.exp.banduapp.repository.BookingRepository;
import org.exp.banduapp.repository.PlaceRepository;
import org.exp.banduapp.service.face.PlaceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final BookingRepository bookingRepository;

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
        List<TimeSlot> bookedSlots = bookingRepository.findBookedTimeSlotsByPlace(place).stream()
                .map(row -> TimeSlot.builder()
                        .startTime((LocalDateTime) row[0])
                        .endTime((LocalDateTime) row[1])
                        .build())
                .toList();

        return PlaceRes.builder()
                .id(place.getId())
                .name(place.getName())
                .description(place.getDescription())
                .address(place.getAddress())
                .capacity(place.getCapacity())
                .pricePerHour(place.getPricePerHour())
                .status(place.getStatus().name())
                .bookedSlots(bookedSlots)
                .build();
    }
}
