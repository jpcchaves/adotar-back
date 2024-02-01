package com.jpcchaves.adotar.presentation.controller.v1;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.pet.PetDto;
import com.jpcchaves.adotar.application.service.pet.contracts.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/api/v1/saved-pets")
@RestController
@CrossOrigin
@Tag(name = "Pets-Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class SavedPetsController {
    private final PetService petService;

    public SavedPetsController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            summary = "Get a list of user saved pets",
            description = "Get a list of user saved pets",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PetDto.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping()
    public ResponseEntity<Set<PetDto>> getUserSavedPets() {
        return ResponseEntity.ok(petService.getUserSavedPets());
    }

    @Operation(summary = "Adds a saved pet for the current user",
            description = "Adds a saved pet for the current user",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ApiMessageResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping("/{petId}")
    public ResponseEntity<ApiMessageResponseDto> addUserSavedPet(@PathVariable(name = "petId") Long petId) {
        return ResponseEntity.ok(petService.addUserSavedPet(petId));
    }

    @Operation(summary = "Unsave a pet",
            description = "Unsave a pet by passing a pet ID",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = ApiMessageResponseDto.class)
                            )
                    ), @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping("/{petId}")
    public ResponseEntity<ApiMessageResponseDto> removeUserSavedPet(@PathVariable(name = "petId") Long petId) {
        return ResponseEntity.ok(petService.removeUserSavedPet(petId));
    }
}
