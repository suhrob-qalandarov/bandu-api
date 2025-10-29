package org.exp.banduapp.models.dto.request.admin;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlaceReq(

        @NotBlank(message = "Joy nomi kiritilishi shart")
        @Size(max = 100, message = "Joy nomi 100 belgidan oshmasligi kerak")
        String name,

        @NotBlank(message = "Tavsif kiritilishi shart")
        String description,

        @NotBlank(message = "Manzil kiritilishi shart")
        @Size(max = 255, message = "Manzil 255 belgidan oshmasligi kerak")
        String address,

        @NotNull(message = "Sig'imi kiritilishi shart")
        @Positive(message = "Sig'im musbat son bo'lishi kerak")
        @Max(value = 1000, message = "Sig'im 1000 dan oshmasligi kerak")
        Integer capacity,

        @NotNull(message = "Soatlik narx kiritilishi shart")
        @PositiveOrZero(message = "Narx nol yoki musbat bo'lishi kerak")
        @Digits(integer = 10, fraction = 2, message = "Narx formati noto'g'ri (masalan: 150000.50)")
        BigDecimal pricePerHour
) {}