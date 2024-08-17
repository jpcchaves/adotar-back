package com.cleanarch.infra.controller.auth;

import com.cleanarch.infra.domain.dto.auth.LoginRequestDTO;
import com.cleanarch.infra.domain.dto.auth.RegisterRequestDTO;
import com.cleanarch.infra.domain.dto.auth.UpdatePasswordDTO;
import com.cleanarch.infra.domain.dto.auth.UpdateUserRequestDTO;
import com.cleanarch.infra.service.auth.AuthService;
import com.cleanarch.usecase.auth.dto.LoginResponseDTO;
import com.cleanarch.usecase.common.dto.MessageResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<LoginResponseDTO> login(
      @RequestBody @Valid LoginRequestDTO requestDTO) {

    return ResponseEntity.ok(authService.login(requestDTO));
  }

  @PostMapping("/register")
  public ResponseEntity<MessageResponseDTO> register(
      @RequestBody @Valid RegisterRequestDTO requestDTO) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(requestDTO));
  }

  @PutMapping("/update-password")
  public ResponseEntity<MessageResponseDTO> updatePassword(
      @RequestBody @Valid UpdatePasswordDTO requestDTO) {

    return ResponseEntity.ok(authService.updatePassword(requestDTO));
  }

  @PutMapping("/update-user-details")
  public ResponseEntity<MessageResponseDTO> updateUserDetails(
      @Valid @RequestBody UpdateUserRequestDTO requestDTO) {

    return ResponseEntity.ok(authService.updateUser(requestDTO));
  }
}
