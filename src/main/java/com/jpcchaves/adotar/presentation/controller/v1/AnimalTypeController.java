package com.jpcchaves.adotar.presentation.controller.v1;

import com.jpcchaves.adotar.application.dto.pet.AnimalTypeDto;
import com.jpcchaves.adotar.application.dto.pet.AnimalTypeMinDto;
import com.jpcchaves.adotar.application.service.animaltype.contracts.AnimalTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/animal-types")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Animal Type-Controller")
public class AnimalTypeController {
  private final AnimalTypeService animalTypeService;

  public AnimalTypeController(AnimalTypeService animalTypeService) {
    this.animalTypeService = animalTypeService;
  }

  @Operation(
      summary = "Get a list of animal types",
      description = "Returns a animal types list",
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content =
                @Content(
                    array =
                        @ArraySchema(
                            schema =
                                @Schema(
                                    implementation = AnimalTypeMinDto.class)))),
        @ApiResponse(
            description = "Bad Request",
            responseCode = "400",
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
  public ResponseEntity<List<AnimalTypeMinDto>> getAll() {
    return ResponseEntity.ok(animalTypeService.getAll());
  }

  @Operation(
      summary = "Gets a animal type by id",
      description = "Gets a animal type by id",
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content =
                @Content(
                    schema = @Schema(implementation = AnimalTypeDto.class))),
        @ApiResponse(
            description = "Bad Request",
            responseCode = "400",
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
  @GetMapping("/{id}")
  public ResponseEntity<AnimalTypeMinDto> getById(
      @PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(animalTypeService.getById(id));
  }
}
