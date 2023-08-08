package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.pet.PetCharacteristicsDto;
import com.jpcchaves.adotar.service.usecases.PetCharacteristicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets-characteristics")
@CrossOrigin(origins = "*")
@Tag(name = "Pet Characteristics-Controller")
public class PetCharacteristicsController {
    private final PetCharacteristicService petCharacteristicService;

    public PetCharacteristicsController(PetCharacteristicService petCharacteristicService) {
        this.petCharacteristicService = petCharacteristicService;
    }

    @GetMapping
    public ResponseEntity<List<PetCharacteristicsDto>> getAll() {
        return ResponseEntity.ok(petCharacteristicService.getAll());
    }
}
