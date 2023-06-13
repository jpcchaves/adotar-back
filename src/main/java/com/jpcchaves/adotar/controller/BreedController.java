package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.pet.BreedDto;
import com.jpcchaves.adotar.service.usecases.BreedService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/breeds")
public class BreedController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping
    public ResponseEntity<List<BreedDto>> findAllByAnimalType(@PathParam("animalTypeId") Long animalTypeId) {
        return ResponseEntity.ok(breedService.findAllByAnimalType(animalTypeId));
    }
}
