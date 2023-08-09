package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.email.ContactEmailDto;
import com.jpcchaves.adotar.service.usecases.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/contact-us")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class ContactUsController {
    private final EmailService emailService;

    public ContactUsController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<ApiMessageResponseDto> sendContactUsMessage(@RequestBody ContactEmailDto contactEmailDto) throws MessagingException {
        return ResponseEntity.ok(emailService.sendContactMessage(contactEmailDto));
    }
}
