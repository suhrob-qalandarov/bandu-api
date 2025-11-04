package org.exp.banduapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autentifikatsiya", description = "Kirish, ro'yxatdan o'tish, tasdiqlash")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Joriy foydalanuvchi", description = "Token orqali foydalanuvchi ma'lumotlari")
    @ApiResponse(responseCode = "200", description = "Foydalanuvchi ma'lumotlari")
    @GetMapping("/me")
    public ResponseEntity<UserRes> getClientData(@AuthenticationPrincipal User user) {
        UserRes userRes = userService.convertToUserResponse(user);
        return ResponseEntity.ok(userRes);
    }

    @Operation(summary = "Kirish", description = "Telefon va parol orqali login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muvaffaqiyatli kirish",
                    content = @Content(schema = @Schema(implementation = LoginRes.class))),
            @ApiResponse(responseCode = "400", description = "Noto'g'ri telefon yoki parol")
    })
    @PostMapping("/login/{phoneNumber}/{password}")
    public ResponseEntity<LoginRes> loginUser(
            @NotBlank(message = "Telefon raqami bo'sh bo'lmasligi kerak")
            @Pattern(
                    regexp = "^(\\+998|998)[0-9]{9}$",
                    message = "Telefon raqami +998 yoki 998 bilan boshlanib, 12 ta raqamdan iborat bo'lishi kerak"
            )
            @PathVariable("phoneNumber") String phoneNumber,

            @NotBlank(message = "Parol bo'sh bo'lmasligi kerak")
            @Size(min = 6, max = 50, message = "Parol kamida 6 belgi bo'lishi kerak")
            @PathVariable("password") String password
    ) {
        LoginRes loginRes = authService.loginClient(phoneNumber, password);
        return ResponseEntity.ok(loginRes);
    }

    @Operation(summary = "Ro'yxatdan o'tish", description = "Yangi foydalanuvchi yaratish → OTP yuboriladi")
    @ApiResponse(responseCode = "200", description = "OTP kodi qaytariladi (masalan: 123456)")
    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@Valid @RequestBody RegisterReq registerReq) {
        String otpCode = authService.registerClientAndSendVerifyCode(registerReq);
        return ResponseEntity.ok(otpCode);
    }

    @Operation(summary = "SMS kod bilan tasdiqlash", description = "OTP orqali hisobni faollashtirish")
    @ApiResponse(responseCode = "200", description = "Hisob faollashtirildi + JWT token")
    @PostMapping("/verify/{phoneNumber}/{otpCode}")
    public ResponseEntity<LoginRes> verifyClient(
            @NotBlank(message = "Telefon raqami bo'sh bo'lmasligi kerak")
            @Pattern(
                    regexp = "^(\\+998|998)[0-9]{9}$",
                    message = "Telefon raqami +998 yoki 998 bilan boshlanib, 12 ta raqamdan iborat bo'lishi kerak"
            )
            @PathVariable("phoneNumber") String phoneNumber,

            @NotBlank(message = "Tasdiqlash kodi bo'sh bo'lmasligi kerak")
            @Size(min = 6, max = 6, message = "Tasdiqlash kodi 6 ta raqamdan iborat bo'lishi kerak")
            @Pattern(regexp = "\\d{6}", message = "Tasdiqlash kodi faqat raqamlardan iborat bo'lishi kerak")
            @PathVariable("otpCode") String otpCode
    ) {
        LoginRes loginRes = authService.verifyClient(phoneNumber, otpCode);
        return ResponseEntity.ok(loginRes);
    }
}
