package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.state.StateDto;
import com.jpcchaves.adotar.service.usecases.StateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
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
}
