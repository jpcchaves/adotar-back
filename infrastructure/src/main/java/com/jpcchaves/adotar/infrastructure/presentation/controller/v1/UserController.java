package com.jpcchaves.adotar.infrastructure.presentation.controller.v1;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserDetailsDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserPictureDto;
import com.jpcchaves.adotar.infrastructure.application.dto.user.UserUpdateNameDTO;
import com.jpcchaves.adotar.infrastructure.application.service.user.contracts.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @Operation(
      summary = "Get user all infos",
      description = "Get the current user's logged in all infos",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(
                  schema = @Schema(implementation = UserDetailsDto.class))),
          @ApiResponse(
              description = "Bad Request",
              responseCode = "400",
              content = @Content),
          @ApiResponse(
              description = "Not Found",
              responseCode = "404",
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
  @GetMapping
  public ResponseEntity<UserDetailsDto> getUserDetails() {
    return ResponseEntity.ok(userService.getUserDetails());
  }

  @PatchMapping("/user-picture")
  public ResponseEntity<ApiMessageResponseDto> updateUserPicture(
      @RequestBody UserPictureDto userPictureDto) {
    return ResponseEntity.ok(userService.updateUserPicture(userPictureDto));
  }

  @PatchMapping("/user-name")
  public ResponseEntity<ApiMessageResponseDto> updateUserFirstAndLastName(
      @RequestBody UserUpdateNameDTO requestDTO) {
    return ResponseEntity.ok(userService.updateFirstAndLastname(requestDTO));
  }
}
