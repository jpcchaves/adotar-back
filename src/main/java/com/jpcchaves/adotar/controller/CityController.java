package com.jpcchaves.adotar.controller;

import com.jpcchaves.adotar.payload.dto.city.CityDto;
import com.jpcchaves.adotar.service.usecases.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getCities(@RequestParam(name = "stateId", required = false) Long stateId) {
        return ResponseEntity.ok(cityService.getAllCities(stateId));
    }
}
