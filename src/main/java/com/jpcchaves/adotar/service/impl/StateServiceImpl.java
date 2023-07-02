package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.domain.entities.State;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.city.CityDto;
import com.jpcchaves.adotar.payload.dto.state.StateDto;
import com.jpcchaves.adotar.repository.CityRepository;
import com.jpcchaves.adotar.repository.StateRepository;
import com.jpcchaves.adotar.service.usecases.StateService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final MapperUtils mapperUtils;

    public StateServiceImpl(StateRepository stateRepository,
                            CityRepository cityRepository,
                            MapperUtils mapperUtils) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<StateDto> getAll() {
        List<State> states = stateRepository.findAll();
        return mapperUtils.parseListObjects(states, StateDto.class);
    }

    @Override
    public StateDto getByNameOrUf(String nameOrUf) {
        State state = stateRepository
                .findByNameOrUf(nameOrUf, nameOrUf)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado"));
        return mapperUtils.parseObject(state, StateDto.class);
    }

    @Override
    public List<CityDto> getAllCitiesByState(Long stateId) {
        List<City> cities = cityRepository.findAllByState_Id(stateId);
        return mapperUtils.parseListObjects(cities, CityDto.class);
    }
}
