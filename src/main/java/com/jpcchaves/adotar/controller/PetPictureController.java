package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.service.usecases.PetPictureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets-pictures")
public class PetPictureController {
    private final PetPictureService petPictureService;

    public PetPictureController(PetPictureService petPictureService) {
        this.petPictureService = petPictureService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<List<PetPictureDto>> getAll(@PathVariable(name = "petId") Long id) {
        return ResponseEntity.ok(petPictureService.getAll(id));
    }

    @GetMapping("/{petId}/{picId}")
    public ResponseEntity<PetPictureDto> getById(@PathVariable(name = "petId") Long petId,
                                                 @PathVariable(name = "picId") Long picId) {
        return ResponseEntity.ok(petPictureService.getById(petId, picId));
    }
}
