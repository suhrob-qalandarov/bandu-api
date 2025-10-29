package org.exp.banduapp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exp.banduapp.config.security.JwtService;
import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.response.LoginRes;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.service.face.AuthService;
import org.exp.banduapp.service.face.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public String registerClientAndSendVerifyCode(RegisterReq registerReq) {
        return userService.saveNewUser(registerReq);
    }

    @Override
    @Transactional
    public LoginRes verifyClient(String phoneNumber, String otpCode) {
        String cleanOtp = otpCode.trim();
        User user = userService.checkOtpAndGetUser(phoneNumber, cleanOtp);

        boolean affected = userService.toggleUserVisibility(user.getId());
        if (affected) log.info("User activated userId={}", user.getId());

        String token = jwtService.generateToken(user);
        UserRes userRes = userService.convertToUserResponse(user);

        return LoginRes.builder()
                .token(token)
                .userRes(userRes)
                .build();
    }

    @Override
    public LoginRes loginClient(String phoneNumber, String password) {
        User user = userService.checkPasswordAndGetUser(phoneNumber, password);
        String token = jwtService.generateToken(user);
        UserRes userRes = userService.convertToUserResponse(user);

        return LoginRes.builder()
                .token(token)
                .userRes(userRes)
                .build();
    }
}
