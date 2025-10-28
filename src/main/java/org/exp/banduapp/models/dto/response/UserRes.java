package org.exp.banduapp.models.dto.response;

import lombok.Builder;
import java.util.Set;

@Builder
public record UserRes (
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        Boolean visibility,
        Set<String> roles
) {}
