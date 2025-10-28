package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.service.face.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserRes getUserDataFromToken(User user) {
        return convertToResponse(user);
    }

    private UserRes convertToResponse(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
        return UserRes.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .visibility(user.isVisibility())
                .roles(roleNames)
                .build();
    }
}
