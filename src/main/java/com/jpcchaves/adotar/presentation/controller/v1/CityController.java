package com.jpcchaves.adotar.presentation.controller.v1;

import com.jpcchaves.adotar.application.dto.city.CityDto;
import com.jpcchaves.adotar.application.service.usecases.v1.CityService;
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
@RequestMapping("/api/v1/cities")
@CrossOrigin
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "City-Controller")
public class CityController {
  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @Operation(
      summary = "Get a list of cities",
      description =
          "Returns a list of cities. By passing none of the parameters, the returned list will contain all cities",
      responses = {
        @ApiResponse(
            description = "Success",
            responseCode = "200",
            content =
                @Content(
                    array =
                        @ArraySchema(
                            schema = @Schema(implementation = CityDto.class)))),
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
  public ResponseEntity<List<CityDto>> getCities(
      @RequestParam(name = "stateId", required = false) Long stateId,
      @RequestParam(name = "uf", required = false) String uf) {
    return ResponseEntity.ok(cityService.getAllCities(stateId, uf));
  }
}
