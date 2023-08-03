package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.service.usecases.PetCardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets/generate-card")
public class PetCardController {
    private final PetCardService petCardService;

    public PetCardController(PetCardService petCardService) {
        this.petCardService = petCardService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<byte[]> generatePetCard(@PathVariable(name = "petId") Long petId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(petCardService.generatePetCard(petId), headers, HttpStatus.OK);
    }
}
