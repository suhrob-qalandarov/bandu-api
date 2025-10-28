package org.exp.banduapp.service.impl;

import jakarta.transaction.Transactional;
import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.Role;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.models.enums.RoleName;
import org.exp.banduapp.repository.UserRepository;
import org.exp.banduapp.service.face.RoleService;
import org.exp.banduapp.service.face.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRes getClientDataFromToken(User user) {
        return convertToResponse(user);
    }

    @Override
    public String saveNewUser(RegisterReq registerReq) {

        boolean existsUserByPhoneNumber = userRepository.existsUserByPhoneNumber(registerReq.phoneNumber());

        if (existsUserByPhoneNumber) {
            throw new RuntimeException("Already exist!");
        }

        User buildUser = convertToEntityFromRegisterReq(registerReq);
        String newOtpCode = generateOneTimeCode();
        buildUser.setOtpCode(newOtpCode);

        userRepository.save(buildUser);
        return newOtpCode;
    }

    @Override
    public Optional<User> checkAndGetUser(String phoneNumber, String otpCode) {
        String fullPhone = getCorrectNumber(phoneNumber);
        return userRepository.findByPhoneNumberAndOtpCode(fullPhone, otpCode);
    }

    @Override
    @Transactional
    public boolean toggleUserVisibility(Long userId) {
        int updated = userRepository.toggleVisibilityById(userId);
        return updated > 0;
    }

    private String generateOneTimeCode() {
        SecureRandom random = new SecureRandom();
        int code = 100_000 + random.nextInt(900_000);
        return String.format("%06d", code);
    }

    private User convertToEntityFromRegisterReq(RegisterReq registerReq) {
        Role userRole = roleService.getOrCreateRoleByName(RoleName.ROLE_USER);
        String fullPhone = getCorrectNumber(registerReq.phoneNumber());
        String encodedPass = passwordEncoder.encode(registerReq.password());

        return User.builder()
                .firstName(registerReq.firstName())
                .lastName(registerReq.lastName())
                .phoneNumber(fullPhone)
                .password(encodedPass)
                .roles(Collections.singleton(userRole))
                .build();
    }

    private String getCorrectNumber(String phoneNumber) {
        return phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber;
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
