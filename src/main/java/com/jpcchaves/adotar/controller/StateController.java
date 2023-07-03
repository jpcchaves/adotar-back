package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.city.CityDto;
import com.jpcchaves.adotar.payload.dto.state.StateDto;
import com.jpcchaves.adotar.service.usecases.StateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
@CrossOrigin("*")
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public ResponseEntity<List<StateDto>> getAll() {
        return ResponseEntity.ok(stateService.getAll());
    }

    @GetMapping("/{nameOrUf}")
    public ResponseEntity<StateDto> getByNameOrUf(@PathVariable String nameOrUf) {
        return ResponseEntity.ok(stateService.getByNameOrUf(nameOrUf));
    }

    @GetMapping("/{stateId}/cities")
    public ResponseEntity<List<CityDto>> getCitiesByStateId(@PathVariable Long stateId) {
        return ResponseEntity.ok(stateService.getAllCitiesByState(stateId));
    }
}
