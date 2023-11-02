package com.jpcchaves.adotar.controller.v2;

import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetMinDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.service.usecases.v2.PetServiceV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/pets")
@CrossOrigin(origins = "*")
@Tag(name = "Pets-Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class PetControllerV2 {

    private final PetServiceV2 petServiceV2;

    public PetControllerV2(PetServiceV2 petServiceV2) {
        this.petServiceV2 = petServiceV2;
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
    public ResponseEntity<ApiResponsePaginatedDto<PetMinDtoV2>> listAll(Pageable pageable) {
        return ResponseEntity.ok(petServiceV2.listAllV2(pageable));
    }
}
