package com.jpcchaves.adotar.infrastructure.presentation.controller.v2;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.infrastructure.application.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.infrastructure.application.service.pet.contracts.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/pets")
@CrossOrigin(origins = "*")
@Tag(name = "Pets-Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class PetControllerV2 {

  private final PetService petService;

  public PetControllerV2(PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  @Operation(
      summary = "Gets a pet list",
      description = "Gets a list of all pets",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(
                  schema =
                  @Schema(
                      implementation = ApiResponsePaginatedDto.class))),
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
  public ResponseEntity<ApiResponsePaginatedDto<PetMinDtoV2>> listAll(
      Pageable pageable) {
    return ResponseEntity.ok(petService.listAll(pageable));
  }

  @GetMapping("/filter")
  public ResponseEntity<ApiResponsePaginatedDto<PetMinDtoV2>>
  filterByAnimalTypes(
      Pageable pageable, @RequestParam List<Long> animalTypesIds) {
    return ResponseEntity.ok(
        petService.filterByAnimalTypes(pageable, animalTypesIds));
  }
}
