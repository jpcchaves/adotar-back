package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.state.StateDto;

import java.util.List;

public interface StateService {
    List<StateDto> getAll();

    StateDto getByNameOrUf(String nameOrUf);

}
