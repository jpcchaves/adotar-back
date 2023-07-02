package com.jpcchaves.adotar.repository;

import com.jpcchaves.adotar.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByState_Id(Long stateId);
}
