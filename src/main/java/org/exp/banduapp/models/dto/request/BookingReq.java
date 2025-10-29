package org.exp.banduapp.models.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record BookingReq(
        @NotNull(message = "Joy ID si kiritilishi shart")
        @Positive(message = "Joy ID musbat bo'lishi kerak")
        Long placeId,

        @NotNull(message = "Boshlanish vaqti kiritilishi shart")
        @FutureOrPresent(message = "Boshlanish vaqti hozirgi yoki kelajakdagi vaqt bo'lishi kerak")
        LocalDateTime startTime,

        @NotNull(message = "Tugash vaqti kiritilishi shart")
        @Future(message = "Tugash vaqti kelajakdagi vaqt bo'lishi kerak")
        LocalDateTime endTime,

        @Size(max = 500, message = "Izoh 500 belgidan oshmasligi kerak")
        String note
) {}