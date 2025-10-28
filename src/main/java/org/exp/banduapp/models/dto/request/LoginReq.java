package org.exp.banduapp.models.dto.request;

public record LoginReq (
        String phoneNumber,
        String password
) {}
