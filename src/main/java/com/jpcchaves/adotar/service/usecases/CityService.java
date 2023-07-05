package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.city.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> getAllCities(Long stateId,
                               String uf);
}
