package org.exp.banduapp.models.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

public record BookingReq(
        Long placeId,
        @FutureOrPresent LocalDateTime startTime,
        @Future LocalDateTime endTime,
        String note
) {}