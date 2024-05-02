package com.jpcchaves.adotar.infrastructure.application.service.location.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.city.CityDto;
import java.util.List;

public interface CityService {

  List<CityDto> getAllCities(Long stateId, String uf);
}
