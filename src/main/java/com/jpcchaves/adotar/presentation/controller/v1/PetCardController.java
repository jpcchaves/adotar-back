package com.jpcchaves.adotar.presentation.controller.v1;

import com.jpcchaves.adotar.application.service.usecases.v1.PetCardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pets/generate-card")
@CrossOrigin(origins = "*")
@Tag(name = "Pet Card-Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class PetCardController {
    private final PetCardService petCardService;
    HttpHeaders headers = new HttpHeaders();

    public PetCardController(PetCardService petCardService) {
        this.petCardService = petCardService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<byte[]> generatePetCard(@PathVariable(name = "petId") Long petId) {
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(petCardService.generatePetCard(petId), headers, HttpStatus.OK);
    }

    @GetMapping("/empty-card")
    public ResponseEntity<byte[]> generateEmptyCard() {
        headers.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(petCardService.generateEmptyCard(), headers, HttpStatus.OK);
    }
}
