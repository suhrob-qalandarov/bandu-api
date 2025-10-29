package org.exp.banduapp.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.repository.UserRepository;
import org.exp.banduapp.service.face.AdminUserService;
import org.exp.banduapp.service.face.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public List<UserRes> getUserResList() {
        return userRepository.findAll().stream()
                .map(userService::convertToUserResponse)
                .toList();
    }

    @Override
    public UserRes getUserRes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userService.convertToUserResponse(user);
    }
}
