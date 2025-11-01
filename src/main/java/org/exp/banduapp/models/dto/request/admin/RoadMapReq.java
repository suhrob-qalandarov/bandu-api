package org.exp.banduapp.models.dto.request.admin;

import java.util.List;

public record RoadMapReq(
        String title,
        String description,
        String result,
        Boolean visibility,
        List<RoadMapTaskReq> roadMapTasksRequestList
){}
