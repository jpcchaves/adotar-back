package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.pet.PetCharacteristicsDto;
import com.jpcchaves.adotar.service.usecases.PetCharacteristicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets-characteristics")
@CrossOrigin(origins = "*")
@Tag(name = "Pet Characteristics-Controller")
public class PetCharacteristicsController {
    private final PetCharacteristicService petCharacteristicService;

    public PetCharacteristicsController(PetCharacteristicService petCharacteristicService) {
        this.petCharacteristicService = petCharacteristicService;
    }

    @GetMapping
    @Operation(
            summary = "Gets a list of pet characteristics",
            description = "Get a list of pet characteristics",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PetCharacteristicsDto.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<List<PetCharacteristicsDto>> getAll() {
        return ResponseEntity.ok(petCharacteristicService.getAll());
    }
}
