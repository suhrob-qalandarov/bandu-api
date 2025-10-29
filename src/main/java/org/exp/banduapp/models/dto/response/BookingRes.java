package org.exp.banduapp.models.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record BookingRes(
        Long id,
        String placeName,
        String placeAddress,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal pricePerHour,
        BigDecimal totalPrice,
        String status,
        String note,
        LocalDateTime expiresAt
) {}