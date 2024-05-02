package com.jpcchaves.adotar.infrastructure.application.utils.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtils {
  private final ModelMapper mapper;

  public MapperUtils(ModelMapper mapper) {
    this.mapper = mapper;
  }

  public <O, D> D parseObject(O origin, Class<D> destination) {
    return mapper.map(origin, destination);
  }

  public <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {

    List<D> destinationObjects = new ArrayList<>();

    for (O o : origin) {
      destinationObjects.add(mapper.map(o, destination));
    }

    return destinationObjects;
  }

  public <O, D> Set<D> parseSetObjects(Set<O> origin, Class<D> destination) {
    Set<D> destinationObjects = new HashSet<>();

    for (O o : origin) {
      destinationObjects.add(mapper.map(o, destination));
    }

    return destinationObjects;
  }
}
