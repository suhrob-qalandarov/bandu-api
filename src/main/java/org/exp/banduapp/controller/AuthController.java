package org.exp.banduapp.controller;

import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.response.LoginRes;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.models.entities.User;
import org.exp.banduapp.service.face.AuthService;
import org.exp.banduapp.service.face.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + AUTH))
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<UserRes> getUserData(@AuthenticationPrincipal User user) {
        UserRes userRes = userService.getUserDataFromToken(user);
        return ResponseEntity.ok(userRes);
    }

    @PostMapping("/register")
    public ResponseEntity<String> getUserData(@RequestBody RegisterReq registerReq) {
        String otpCode = authService.saveDataAndSendVerifyCode(registerReq);
        return ResponseEntity.ok(otpCode);
    }

    @PostMapping("/verify")
    public ResponseEntity<LoginRes> getUserData(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("otpCode") @Size(min = 6, max = 6) String otpCode
    ) {
        LoginRes loginRes = authService.verifyAndSendUserData(phoneNumber, otpCode);
        return ResponseEntity.ok(loginRes);
    }
}
