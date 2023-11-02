package com.jpcchaves.adotar.controller.v2;

import com.jpcchaves.adotar.payload.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.payload.dto.auth.LoginDtoV2;
import com.jpcchaves.adotar.service.usecases.v1.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/auth")
@CrossOrigin
@Tag(name = "Auth-Controller")
public class AuthControllerV2 {
    private final AuthService authService;

    public AuthControllerV2(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponseDto> loginV2(@Valid @RequestBody LoginDtoV2 loginDtoV2) {
        return ResponseEntity.ok(authService.loginV2(loginDtoV2));
    }
}
