package com.jpcchaves.adotar.controller.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.service.usecases.v1.PetPictureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets-pictures")
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Pet Picture-Controller")
public class PetPictureController {
    private final PetPictureService petPictureService;

    public PetPictureController(PetPictureService petPictureService) {
        this.petPictureService = petPictureService;
    }

    @Operation(
            summary = "Get a list of pet pictures",
            description = "Get a list of pet pictures by passing a pet id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PetPictureDto.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping("/{petId}")
    public ResponseEntity<List<PetPictureDto>> getAll(@PathVariable(name = "petId") Long id) {
        return ResponseEntity.ok(petPictureService.getAll(id));
    }

    @Operation(
            summary = "Get a pet pictures",
            description = "Get a pet picture by passing a pet id and a picture id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PetPictureDto.class
                                    )

                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping("/{petId}/{picId}")
    public ResponseEntity<PetPictureDto> getById(
            @PathVariable(name = "petId") Long petId,
            @PathVariable(name = "picId") Long picId) {
        return ResponseEntity.ok(petPictureService.getById(petId, picId));
    }

    @Operation(
            summary = "Creates a pet picture",
            description = "Creates a pet picture by passing a pet id as path parameter and pet picture JSON representation",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PetPictureDto.class
                                    )

                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping("/{petId}")
    public ResponseEntity<PetPictureDto> create(
            @PathVariable(name = "petId") Long petId,
            @Valid @RequestBody PetPictureDto petPictureDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petPictureService.create(petId, petPictureDto));
    }

    @Operation(
            summary = "Updated a pet picture",
            description = "Updated a pet picture by passing a pet id as path parameter and pet picture JSON representation",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PetPictureDto.class
                                    )

                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping("/{petId}/{picId}")
    public ResponseEntity<PetPictureDto> update(
            @PathVariable(name = "petId") Long petId,
            @PathVariable(name = "picId") Long picId,
            @Valid @RequestBody PetPictureDto petPictureDto) {
        return ResponseEntity.ok(petPictureService.update(petId, picId, petPictureDto));
    }

    @DeleteMapping("/{petId}/{picId}")
    @Operation(
            summary = "Delete a pet picture",
            description = "Delete a pet picture by its id and a pet it",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = ApiMessageResponseDto.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ApiMessageResponseDto> delete(
            @PathVariable(name = "petId") Long petId,
            @PathVariable(name = "picId") Long picId) {
        return ResponseEntity.ok(petPictureService.delete(petId, picId));
    }

    @PatchMapping("/{petId}/{picId}")
    public ResponseEntity<ApiMessageResponseDto> markAsFavorite(
            @PathVariable(name = "petId") Long petId,
            @PathVariable(name = "picId") Long picId) {
        return ResponseEntity.ok(petPictureService.markAsFavorite(petId, picId));
    }
}
