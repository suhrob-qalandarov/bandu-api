package org.exp.banduapp.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + AUTH))
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<UserRes> getClientData(@AuthenticationPrincipal User user) {
        UserRes userRes = userService.convertToUserResponse(user);
        return ResponseEntity.ok(userRes);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> loginUser(
            @NotBlank(message = "Telefon raqami bo'sh bo'lmasligi kerak")
            @Pattern(
                    regexp = "^(\\+998|998)[0-9]{9}$",
                    message = "Telefon raqami +998 yoki 998 bilan boshlanib, 12 ta raqamdan iborat bo'lishi kerak"
            )
            @RequestParam("phoneNumber") String phoneNumber,

            @NotBlank(message = "Parol bo'sh bo'lmasligi kerak")
            @Size(min = 6, max = 50, message = "Parol kamida 6 belgi bo'lishi kerak")
            @RequestParam("password") String password
    ) {
        LoginRes loginRes = authService.loginClient(phoneNumber, password);
        return ResponseEntity.ok(loginRes);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@Valid @RequestBody RegisterReq registerReq) {
        String otpCode = authService.registerClientAndSendVerifyCode(registerReq);
        return ResponseEntity.ok(otpCode);
    }

    @PostMapping("/verify")
    public ResponseEntity<LoginRes> verifyClient(
            @NotBlank(message = "Telefon raqami bo'sh bo'lmasligi kerak")
            @Pattern(
                    regexp = "^(\\+998|998)[0-9]{9}$",
                    message = "Telefon raqami +998 yoki 998 bilan boshlanib, 12 ta raqamdan iborat bo'lishi kerak"
            )
            @RequestParam("phoneNumber") String phoneNumber,

            @NotBlank(message = "Tasdiqlash kodi bo'sh bo'lmasligi kerak")
            @Size(min = 6, max = 6, message = "Tasdiqlash kodi 6 ta raqamdan iborat bo'lishi kerak")
            @Pattern(regexp = "\\d{6}", message = "Tasdiqlash kodi faqat raqamlardan iborat bo'lishi kerak")
            @RequestParam("otpCode") String otpCode
    ) {
        LoginRes loginRes = authService.verifyClient(phoneNumber, otpCode);
        return ResponseEntity.ok(loginRes);
    }
}
