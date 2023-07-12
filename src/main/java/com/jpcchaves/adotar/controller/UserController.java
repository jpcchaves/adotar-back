package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.service.usecases.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User-Controller")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDetailsDto> getUserDetails() {
        return ResponseEntity.ok(userService.getUserDetails());
    }
}
