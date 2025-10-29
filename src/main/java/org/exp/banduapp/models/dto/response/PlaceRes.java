package org.exp.banduapp.models.dto.response;

import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Builder
public record PlaceRes(
        Long id,
        String name,
        String description,
        String address,
        Integer capacity,
        BigDecimal pricePerHour,
        String status,
        List<TimeSlot> bookedSlots
) {}