package com.jpcchaves.adotar.service.usecases;


import java.io.IOException;

public interface PetCardService {
    byte[] generatePetCard(Long petId) throws IOException;
}
