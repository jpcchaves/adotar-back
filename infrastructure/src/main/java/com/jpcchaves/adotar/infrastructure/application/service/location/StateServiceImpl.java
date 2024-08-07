package com.jpcchaves.adotar.infrastructure.application.service.location;

import com.jpcchaves.adotar.infrastructure.application.dto.state.StateDto;
import com.jpcchaves.adotar.infrastructure.application.service.location.contracts.StateService;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.model.State;
import com.jpcchaves.adotar.infrastructure.infra.repository.StateRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {

  private final StateRepository stateRepository;
  private final MapperUtils mapperUtils;

  public StateServiceImpl(
      StateRepository stateRepository, MapperUtils mapperUtils) {
    this.stateRepository = stateRepository;
    this.mapperUtils = mapperUtils;
  }

  @Override
  public List<StateDto> getAll() {
    List<State> states = stateRepository.findAll();
    return mapperUtils.parseListObjects(states, StateDto.class);
  }

  @Override
  public StateDto getByNameOrUf(String nameOrUf) {
    State state =
        stateRepository
            .findByNameOrUf(nameOrUf, nameOrUf)
            .orElseThrow(
                () -> new ResourceNotFoundException("Estado não encontrado"));
    return mapperUtils.parseObject(state, StateDto.class);
  }
}
