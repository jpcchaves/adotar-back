package com.jpcchaves.adotar.presentation.controller.v1;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.application.service.mail.contracts.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/contact-us")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
@Tag(name = "Contact Us-Controller")
public class ContactUsController {
  private final EmailService emailService;

  public ContactUsController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping
  @Operation(
      summary = "Sends a contact email",
      description = "Sends a contact email to the Adote.ME support",
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
            description = "Internal Error",
            responseCode = "500",
            content = @Content),
      })
  public ResponseEntity<ApiMessageResponseDto> sendContactUsMessage(
      @RequestBody ContactEmailDto contactEmailDto) throws MessagingException {
    return ResponseEntity.ok(emailService.sendContactMessage(contactEmailDto));
  }
}
