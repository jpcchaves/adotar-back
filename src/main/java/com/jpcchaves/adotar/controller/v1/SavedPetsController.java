package com.jpcchaves.adotar.controller.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.service.usecases.v1.PetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/api/v1/saved-pets")
@RestController
@CrossOrigin
@Tag(name = "Pets-Controller")
@SecurityRequirement(name = "Bearer Authentication")
public class SavedPetsController {
    private final PetService petService;

    public SavedPetsController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping()
    public ResponseEntity<Set<PetDto>> getUserSavedPets() {
        return ResponseEntity.ok(petService.getUserSavedPets());
    }

    @PostMapping("/{petId}")
    public ResponseEntity<ApiMessageResponseDto> addUserSavedPet(@PathVariable(name = "petId") Long petId) {
        return ResponseEntity.ok(petService.addUserSavedPet(petId));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<ApiMessageResponseDto> removeUserSavedPet(@PathVariable(name = "petId") Long petId) {
        return ResponseEntity.ok(petService.removeUserSavedPet(petId));
    }
}
