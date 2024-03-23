package com.jpcchaves.adotar.application.service.impl.v1;

import com.jpcchaves.adotar.application.dto.state.StateDto;
import com.jpcchaves.adotar.application.service.usecases.StateService;
import com.jpcchaves.adotar.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.domain.model.State;
import com.jpcchaves.adotar.infra.repository.StateRepository;
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
