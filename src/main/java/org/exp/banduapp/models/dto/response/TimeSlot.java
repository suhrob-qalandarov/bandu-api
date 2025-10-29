package org.exp.banduapp.models.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TimeSlot (
        LocalDateTime startTime,
        LocalDateTime endTime
){}