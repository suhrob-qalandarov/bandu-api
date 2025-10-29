package org.exp.banduapp.models.dto.request;

import jakarta.validation.constraints.*;

public record RegisterReq(
        @NotBlank(message = "Ism bo'sh bo'lmasligi kerak")
        @Size(min = 2, max = 50, message = "Ism 2-50 belgi oralig'ida bo'lishi kerak")
        String firstName,

        @NotBlank(message = "Familiya bo'sh bo'lmasligi kerak")
        @Size(min = 2, max = 50, message = "Familiya 2-50 belgi oralig'ida bo'lishi kerak")
        String lastName,

        @NotBlank(message = "Telefon raqami bo'sh bo'lmasligi kerak")
        @Pattern(
                regexp = "^(\\+998|998)[0-9]{9}$",
                message = "Telefon raqami +998 yoki 998 bilan boshlanib, 12 ta raqamdan iborat bo'lishi kerak"
        )
        String phoneNumber,

        @NotBlank(message = "Parol bo'sh bo'lmasligi kerak")
        @Size(min = 6, message = "Parol kamida 6 belgi bo'lishi kerak")
        String password
) {}