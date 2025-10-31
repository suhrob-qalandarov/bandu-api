package org.exp.banduapp.models.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RoadMapRes(
        Long id,
        String title,
        String description,
        String result,
        List<RoadMapTaskRes> tasks
){}
