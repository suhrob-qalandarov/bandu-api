package org.exp.banduapp.models.dto.response;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record PlaceRes(
        Long id,
        String name,
        String address,
        Integer capacity,
        BigDecimal pricePerHour,
        String status
) {}