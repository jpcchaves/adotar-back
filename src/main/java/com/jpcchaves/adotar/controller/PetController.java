package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.service.usecases.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> listAll() {
        return ResponseEntity.ok(petService.listAll());
    }
}
