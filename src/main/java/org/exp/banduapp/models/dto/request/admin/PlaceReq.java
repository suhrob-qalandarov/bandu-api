package org.exp.banduapp.models.dto.request.admin;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlaceReq(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotBlank(message = "Address is required")
        @Size(max = 255, message = "Address must not exceed 255 characters")
        String address,

        @NotNull(message = "Capacity is required")
        @Positive(message = "Capacity must be positive")
        @Max(value = 1000, message = "Capacity cannot exceed 1000")
        Integer capacity,

        @NotNull(message = "Price per hour is required")
        @PositiveOrZero(message = "Price must be zero or positive")
        @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
        BigDecimal pricePerHour
) {}