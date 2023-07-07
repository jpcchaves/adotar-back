package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.pet.AnimalTypeMinDto;
import com.jpcchaves.adotar.service.usecases.AnimalTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animal-types")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "Bearer Authentication")
public class AnimalTypeController {
    private final AnimalTypeService animalTypeService;

    public AnimalTypeController(AnimalTypeService animalTypeService) {
        this.animalTypeService = animalTypeService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalTypeMinDto>> getAll() {
        return ResponseEntity.ok(animalTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalTypeMinDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(animalTypeService.getById(id));
    }
}
