package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.city.CityDto;
import com.jpcchaves.adotar.payload.dto.state.StateDto;

import java.util.List;

public interface StateService {
    List<StateDto> getAll();

    StateDto getByNameOrUf(String nameOrUf);

    List<CityDto> getAllCitiesByState(Long stateId);
}
