package org.exp.banduapp.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.service.face.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping(API + V1 + ADMIN + USERS)
@RequiredArgsConstructor
@Tag(name = "Admin: Foydalanuvchilar", description = "Faqat ADMIN uchun foydalanuvchi boshqaruvi")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Operation(summary = "Barcha foydalanuvchilar", description = "Tizimdagi barcha foydalanuvchilar ro'yxati")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "200", description = "Foydalanuvchilar ro'yxati")
    @GetMapping
    public ResponseEntity<List<UserRes>> getUsers() {
        return ResponseEntity.ok(adminUserService.getUserResList());
    }

    @Operation(summary = "Foydalanuvchi ma'lumotlari", description = "ID bo'yicha bitta foydalanuvchi")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Foydalanuvchi topildi"),
            @ApiResponse(responseCode = "400", description = "Foydalanuvchi topilmadi")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserRes> getUser(
            @PathVariable @Parameter(description = "Foydalanuvchi ID si") Long userId
    ) {
        return ResponseEntity.ok(adminUserService.getUserRes(userId));
    }
}
