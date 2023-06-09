package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.PetDto;
import com.jpcchaves.adotar.service.usecases.PetService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<ApiResponsePaginatedDto<PetDto>> listAll(Pageable pageable) {
        return ResponseEntity.ok(petService.listAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(petService.getById(id));
    }

}
