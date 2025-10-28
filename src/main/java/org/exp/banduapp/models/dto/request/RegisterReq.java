package org.exp.banduapp.models.dto.request;

public record RegisterReq (
      String firstName,
      String lastName,
      String phoneNumber,
      String password
) {}
