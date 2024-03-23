package com.jpcchaves.adotar.application.service.usecases;

import com.jpcchaves.adotar.application.dto.city.CityDto;
import java.util.List;

public interface CityService {

  List<CityDto> getAllCities(Long stateId, String uf);
}
