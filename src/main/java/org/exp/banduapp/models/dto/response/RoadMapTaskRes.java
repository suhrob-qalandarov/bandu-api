package org.exp.banduapp.models.dto.response;

import lombok.Builder;

@Builder
public record RoadMapTaskRes(
        Long id,
        String task,
        boolean completed
){}
