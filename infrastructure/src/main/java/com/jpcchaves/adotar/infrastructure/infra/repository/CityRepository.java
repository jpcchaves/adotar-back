package com.jpcchaves.adotar.infrastructure.infra.repository;

import com.jpcchaves.adotar.infrastructure.domain.model.City;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
  List<City> findAllByState_Id(Long stateId);

  List<City> findAllByState_UfIgnoreCase(String uf);

  Optional<City> findCityByIbge(Long cityIbge);

  Optional<City> findCityByNameIgnoreCase(String name);
}
