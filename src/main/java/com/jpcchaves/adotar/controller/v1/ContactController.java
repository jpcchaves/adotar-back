package com.jpcchaves.adotar.controller.v1;

import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.service.usecases.v1.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Contact-Controller")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Operation(
            summary = "Get user's contact",
            description = "Get the current user's logged in contact",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ContactDto.class
                                    )

                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping
    public ResponseEntity<ContactDto> getUserContact() {
        return ResponseEntity.ok(contactService.getUserContact());
    }

    // todo: implement create endpoint

    @Operation(
            summary = "Update user's contact",
            description = "Update the current user's logged in contact",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ContactDto.class
                                    )

                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping
    public ResponseEntity<ContactDto> updateUserContact(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(contactService.updateUserContact(contactDto));
    }
}
