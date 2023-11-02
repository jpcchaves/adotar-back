package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.State;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.state.StateDto;
import com.jpcchaves.adotar.repository.StateRepository;
import com.jpcchaves.adotar.service.usecases.v1.StateService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final MapperUtils mapperUtils;

    public StateServiceImpl(StateRepository stateRepository,
                            MapperUtils mapperUtils) {
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
        State state = stateRepository
                .findByNameOrUf(nameOrUf, nameOrUf)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado"));
        return mapperUtils.parseObject(state, StateDto.class);
    }


}
