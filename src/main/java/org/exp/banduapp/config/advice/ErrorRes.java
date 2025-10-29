package org.exp.banduapp.config.advice;

import java.util.Map;

public record ErrorRes(
        String status,
        String message,
        Map<String, String> details
) {
    public ErrorRes(String status, String message) {
        this(status, message, null);
    }
}
