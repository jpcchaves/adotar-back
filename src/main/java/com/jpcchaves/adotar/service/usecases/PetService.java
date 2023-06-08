package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.domain.entities.Pet;

import java.util.List;

public interface PetService {
    List<Pet> listAll();
}
