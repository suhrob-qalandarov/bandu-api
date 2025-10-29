package org.exp.banduapp.controller.admin;

import lombok.RequiredArgsConstructor;
import org.exp.banduapp.models.dto.response.UserRes;
import org.exp.banduapp.service.face.AdminUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.exp.banduapp.util.Constants.*;

@RestController
@RequestMapping((API + V1 + ADMIN + USERS))
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserRes>> getUsers() {
        List<UserRes> userResList = adminUserService.getUserResList();
        return new ResponseEntity<>(userResList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserRes> getPlace(@PathVariable Long userId) {
        UserRes userRes = adminUserService.getUserRes(userId);
        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }
}
