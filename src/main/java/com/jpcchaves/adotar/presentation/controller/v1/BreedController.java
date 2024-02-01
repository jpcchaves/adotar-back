package com.jpcchaves.adotar.presentation.controller.v1;

import com.jpcchaves.adotar.application.dto.pet.BreedDto;
import com.jpcchaves.adotar.application.service.usecases.v1.BreedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/breeds")
@CrossOrigin("*")
@Tag(name = "Breed-Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class BreedController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping
    @Operation(
            summary = "Gets a list of breeds",
            description = "Get a list of breeds by animal type (passing an animal type id)",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = BreedDto.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<List<BreedDto>> findAllByAnimalType(@PathParam("animalTypeId") Long animalTypeId) {
        return ResponseEntity.ok(breedService.findAllByAnimalType(animalTypeId));
    }
}
