package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.City;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.payload.dto.city.CityDto;
import com.jpcchaves.adotar.repository.CityRepository;
import com.jpcchaves.adotar.service.usecases.CityService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final MapperUtils mapperUtils;

    public CityServiceImpl(CityRepository cityRepository,
                           MapperUtils mapperUtils) {
        this.cityRepository = cityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<CityDto> getAllCities(Long stateId,
                                      String uf) {
        List<City> cities = new ArrayList<>();

        if (stateId != null && uf != null) {
            throw new BadRequestException("Informe apenas um parâmetro para realizar a busca");
        }

        if (stateId == null && uf == null) {
            cities.addAll(cityRepository.findAll());
        }

        if (stateId != null) {
            cities.addAll(cityRepository.findAllByState_Id(stateId));
        }

        if (stateId == null && uf != null) {
            cities.addAll(cityRepository.findAllByState_UfIgnoreCase(uf));
        }

        return mapperUtils.parseListObjects(cities, CityDto.class);
    }
}
