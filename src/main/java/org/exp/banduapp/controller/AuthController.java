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
import org.exp.banduapp.models.dto.request.LoginReq;
import org.exp.banduapp.models.dto.request.RegisterReq;
import org.exp.banduapp.models.dto.request.VerificationReq;
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
@Tag(name = "Authentication: ", description = "Login, Registration, Verification, Password Reset")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Current user", description = "Client data from token")
    @ApiResponse(responseCode = "200", description = "Client information")
    @GetMapping("/me")
    public ResponseEntity<UserRes> getClientData(@AuthenticationPrincipal User user) {
        UserRes userRes = userService.convertToUserResponse(user);
        return ResponseEntity.ok(userRes);
    }

    @Operation(summary = "Sign in", description = "Login via phone and password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful login",
                    content = @Content(schema = @Schema(implementation = LoginRes.class))),
            @ApiResponse(responseCode = "400", description = "Incorrect phone or password")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginRes> loginUser(@RequestBody LoginReq loginReq) {
        LoginRes loginRes = authService.loginClient(loginReq.phoneNumber(), loginReq.password());
        return ResponseEntity.ok(loginRes);
    }

    @Operation(summary = "Sign up", description = "Create a new user → OTP will be sent")
    @ApiResponse(responseCode = "200", description = "OTP code will be returned (for example: 123456)")
    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@Valid @RequestBody RegisterReq registerReq) {
        String otpCode = authService.registerClientAndSendVerifyCode(registerReq);
        return ResponseEntity.ok(otpCode);
    }

    @Operation(summary = "Confirm with OTP code", description = "Account activation via OTP")
    @ApiResponse(responseCode = "200", description = "Account activated - (UserData + JWT token)")
    @PostMapping("/verify")
    public ResponseEntity<LoginRes> verifyClient(@RequestBody VerificationReq verificationReq) {
        LoginRes loginRes = authService.verifyClient(verificationReq.phoneNumber(), verificationReq.otpCode());
        return ResponseEntity.ok(loginRes);
    }
}
