package org.exp.banduapp.models.dto.response;

import lombok.Builder;

@Builder
public record LoginRes (
        String token,
        UserRes userRes
) {}
