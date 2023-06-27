package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetCreateRequestDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.payload.dto.pet.PetUpdateRequestDto;
import com.jpcchaves.adotar.service.usecases.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin(origins = "*")
@Tag(name = "Pets-Controller")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    @Operation(summary = "Gets a pet list",
            description = "Gets a list of all pets",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = ApiResponsePaginatedDto.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ApiResponsePaginatedDto<PetDto>> listAll(Pageable pageable) {
        return ResponseEntity.ok(petService.listAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a pet by id",
            description = "Gets a pet by id by passing a pet id as a path variable",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = PetDto.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<PetDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(petService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Creates a new pet",
            description = "Creates a new pet by passing a JSON representation of the pet",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ApiMessageResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ApiMessageResponseDto> create(@Valid @RequestBody PetCreateRequestDto petDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.create(petDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a pet",
            description = "Updates a pet by passing a pet ID and it's JSON representation",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = ApiMessageResponseDto.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ApiMessageResponseDto> update(@PathVariable(name = "id") Long id,
                                                        @Valid @RequestBody PetUpdateRequestDto petUpdateRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.update(id, petUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a pet",
            description = "Deletes a pet by passing a pet ID",
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
    public ResponseEntity<ApiMessageResponseDto> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(petService.delete(id));
    }
}
