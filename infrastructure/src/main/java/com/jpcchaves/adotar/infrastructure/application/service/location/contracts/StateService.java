package com.jpcchaves.adotar.infrastructure.application.service.location.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.state.StateDto;
import java.util.List;

public interface StateService {
  List<StateDto> getAll();

  StateDto getByNameOrUf(String nameOrUf);
}
