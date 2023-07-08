package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.service.usecases.ContactService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin
@SecurityRequirement(name = "Bearer Authentication")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<ContactDto> getUserContact() {
        return ResponseEntity.ok(contactService.getUserContact());
    }

    @PutMapping
    public ResponseEntity<ContactDto> updateUserContact(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.updateUserContact(contactDto));
    }
}
