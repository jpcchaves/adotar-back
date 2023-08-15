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
        assertParamsNotNull(stateId, uf);

        List<City> cities;

        if (isParamPresent(stateId)) {
            cities = cityRepository.findAllByState_Id(stateId);
        } else if (isParamPresent(uf)) {
            cities = cityRepository.findAllByState_UfIgnoreCase(uf);
        } else {
            cities = cityRepository.findAll();
        }

        return mapperUtils.parseListObjects(cities, CityDto.class);
    }

    private void assertParamsNotNull(Long stateId,
                                     String uf) {
        if (stateId != null && uf != null) {
            throw new BadRequestException("Informe apenas um parâmetro para realizar a busca");
        }
    }

    private boolean isParamPresent(Long stateId) {
        return stateId != null;
    }

    private boolean isParamPresent(String uf) {
        return uf != null;
    }

}
