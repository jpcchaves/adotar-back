package com.jpcchaves.adotar.infrastructure.presentation.controller.v1;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.LoginDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.RegisterRequestDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.RegisterResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.TokenDto;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.UpdateUserPasswordRequestDTO;
import com.jpcchaves.adotar.infrastructure.application.dto.auth.UpdateUserRequestDto;
import com.jpcchaves.adotar.infrastructure.application.service.auth.contracts.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@Tag(name = "Auth-Controller")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Operation(
      summary = "Authenticates an user",
      description =
          "Authenticate an user using the provided username/email and password",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(
                  schema =
                  @Schema(implementation = JwtAuthResponseDto.class))),
          @ApiResponse(
              description = "Bad Request",
              responseCode = "400",
              content = @Content),
          @ApiResponse(
              description = "Unauthorized",
              responseCode = "401",
              content = @Content),
          @ApiResponse(
              description = "Internal Error",
              responseCode = "500",
              content = @Content),
      })
  @PostMapping(value = {"/login", "/signin"})
  public ResponseEntity<JwtAuthResponseDto> login(
      @Valid @RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(authService.login(loginDto));
  }

  @Operation(
      summary = "Register an user",
      description =
          "Register an user using the JSON representation of the registration",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "201",
              content =
              @Content(
                  schema =
                  @Schema(implementation = RegisterResponseDto.class))),
          @ApiResponse(
              description = "Bad Request",
              responseCode = "400",
              content = @Content),
          @ApiResponse(
              description = "Unauthorized",
              responseCode = "401",
              content = @Content),
          @ApiResponse(
              description = "Internal Error",
              responseCode = "500",
              content = @Content),
      })
  @PostMapping(value = {"/register", "/signup"})
  public ResponseEntity<RegisterResponseDto> register(
      @Valid @RequestBody RegisterRequestDto registerDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(registerDto));
  }

  @Operation(
      summary = "Updates an user",
      description =
          "Updates an user using the JSON representation of the updated user",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(
                  schema =
                  @Schema(implementation = ApiMessageResponseDto.class))),
          @ApiResponse(
              description = "Bad Request",
              responseCode = "400",
              content = @Content),
          @ApiResponse(
              description = "Unauthorized",
              responseCode = "401",
              content = @Content),
          @ApiResponse(
              description = "Internal Error",
              responseCode = "500",
              content = @Content),
      })
  @PutMapping("/update/{id}")
  @SecurityRequirement(name = "Bearer Authentication")
  public ResponseEntity<ApiMessageResponseDto> update(
      @Valid @RequestBody UpdateUserRequestDto updateUserDto,
      @PathVariable(name = "id") Long id) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(authService.update(updateUserDto, id));
  }

  @PutMapping("update-password")
  @SecurityRequirement(name = "Bearer Authentication")
  public ResponseEntity<ApiMessageResponseDto> updatePassword(
      @Valid @RequestBody UpdateUserPasswordRequestDTO requestDTO) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(authService.updatePassword(requestDTO));
  }

  @Operation(
      summary = "Verify the validity of the JWT Token",
      description =
          "Verify the validity of the JWT Token, if valid, returns the user details to persist the login",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(
                  schema =
                  @Schema(implementation = JwtAuthResponseDto.class))),
          @ApiResponse(
              description = "Bad Request",
              responseCode = "400",
              content = @Content),
          @ApiResponse(
              description = "Unauthorized",
              responseCode = "401",
              content = @Content),
          @ApiResponse(
              description = "Internal Error",
              responseCode = "500",
              content = @Content),
      })
  @PostMapping("/verify-token")
  public ResponseEntity<JwtAuthResponseDto> verifyToken(
      @Valid @RequestBody TokenDto tokenDto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(authService.verifyToken(tokenDto));
  }
}
