package com.jpcchaves.adotar.presentation.controller.v2;

import com.jpcchaves.adotar.application.dto.auth.JwtAuthResponseDto;
import com.jpcchaves.adotar.application.dto.auth.LoginDtoV2;
import com.jpcchaves.adotar.application.service.usecases.v1.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Authenticates an user",
            description = "Authenticate an user using the provided email and password",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = JwtAuthResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponseDto> loginV2(@Valid @RequestBody LoginDtoV2 loginDtoV2) {
        return ResponseEntity.ok(authService.loginV2(loginDtoV2));
    }
}
