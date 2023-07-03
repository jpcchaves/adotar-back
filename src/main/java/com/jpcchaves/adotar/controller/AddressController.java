package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.address.AddressDto;
import com.jpcchaves.adotar.service.usecases.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@CrossOrigin
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<AddressDto> getUserAddress() {
        return ResponseEntity.ok(addressService.getUserAddress());
    }

    @PutMapping
    public ResponseEntity<AddressDto> updateUserAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateUserAddress(addressDto));
    }
}
