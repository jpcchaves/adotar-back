package com.jpcchaves.adotar.application.service.location;

import com.jpcchaves.adotar.application.dto.city.CityDto;
import com.jpcchaves.adotar.application.service.location.contracts.CityService;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.domain.exception.BadRequestException;
import com.jpcchaves.adotar.domain.model.City;
import com.jpcchaves.adotar.infra.repository.CityRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
  private final CityRepository cityRepository;
  private final MapperUtils mapperUtils;

  public CityServiceImpl(
      CityRepository cityRepository, MapperUtils mapperUtils) {
    this.cityRepository = cityRepository;
    this.mapperUtils = mapperUtils;
  }

  @Override
  public List<CityDto> getAllCities(Long stateId, String uf) {
    validateSingleQueryParam(stateId, uf);

    List<City> cities;

    if (isParamPresent(stateId)) {
      cities = fetchCitiesByState(stateId);
    } else if (isParamPresent(uf)) {
      cities = fetchCitiesByState(uf);
    } else {
      cities = fetchAllCities();
    }

    return mapperUtils.parseListObjects(cities, CityDto.class);
  }

  private List<City> fetchCitiesByState(Long stateId) {
    return cityRepository.findAllByState_Id(stateId);
  }

  private List<City> fetchCitiesByState(String uf) {
    return cityRepository.findAllByState_UfIgnoreCase(uf);
  }

  private List<City> fetchAllCities() {
    return cityRepository.findAll();
  }

  private void validateSingleQueryParam(Long stateId, String uf) {
    if (stateId != null && uf != null) {
      throw new BadRequestException(
          "Informe apenas um parâmetro para realizar a busca");
    }
  }

  private boolean isParamPresent(Long stateId) {
    return stateId != null;
  }

  private boolean isParamPresent(String uf) {
    return uf != null;
  }
}
