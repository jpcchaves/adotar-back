package com.jpcchaves.adotar.infrastructure.presentation.controller.v1;

import com.jpcchaves.adotar.infrastructure.application.dto.state.StateDto;
import com.jpcchaves.adotar.infrastructure.application.service.location.contracts.StateService;
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
@RequestMapping("/api/v1/states")
@CrossOrigin("*")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "State-Controller")
public class StateController {

  private final StateService stateService;

  public StateController(StateService stateService) {
    this.stateService = stateService;
  }

  @Operation(
      summary = "Get a BR states list",
      description = "Returns a list of states",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(
                  array =
                  @ArraySchema(
                      schema =
                      @Schema(implementation = StateDto.class)))),
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
  public ResponseEntity<List<StateDto>> getAll() {
    return ResponseEntity.ok(stateService.getAll());
  }

  @Operation(
      summary = "Get a state",
      description =
          "Returns a state by passing it's name or uf as a path variable 'nameOrUf'",
      responses = {
          @ApiResponse(
              description = "Success",
              responseCode = "200",
              content =
              @Content(schema = @Schema(implementation = StateDto.class))),
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
  @GetMapping("/{nameOrUf}")
  public ResponseEntity<StateDto> getByNameOrUf(@PathVariable String nameOrUf) {
    return ResponseEntity.ok(stateService.getByNameOrUf(nameOrUf));
  }
}
