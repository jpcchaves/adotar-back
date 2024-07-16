package com.cleanarch.infra.controller.auth;

import com.cleanarch.infra.domain.dto.auth.RegisterRequestDTO;
import com.cleanarch.infra.service.auth.AuthService;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login() {
    return ResponseEntity.ok("Login accessed");
  }

  @PostMapping("/register")
  public ResponseEntity<MessageResponseDTO> register(
      @RequestBody @Valid
      RegisterRequestDTO requestDTO
  ) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(requestDTO));
  }
}