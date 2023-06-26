package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.service.usecases.PetPictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{petId}")
    public ResponseEntity<PetPictureDto> create(@PathVariable(name = "petId") Long petId,
                                                @RequestBody PetPictureDto petPictureDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petPictureService.create(petId, petPictureDto));
    }

    @PutMapping("/{petId}/{picId}")
    public ResponseEntity<PetPictureDto> update(@PathVariable(name = "petId") Long petId,
                                                @PathVariable(name = "picId") Long picId,
                                                @RequestBody PetPictureDto petPictureDto) {
        return ResponseEntity.ok(petPictureService.update(petId, picId, petPictureDto));
    }

    @DeleteMapping("/{petId}/{picId}")
    public ResponseEntity<ApiMessageResponseDto> delete(@PathVariable(name = "petId") Long petId,
                                                        @PathVariable(name = "picId") Long picId) {
        return ResponseEntity.ok(petPictureService.delete(petId, picId));
    }
}
