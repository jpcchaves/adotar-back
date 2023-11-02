package com.jpcchaves.adotar.service.usecases.v1;


public interface PetCardService {
    byte[] generatePetCard(Long petId);

    byte[] generateEmptyCard();
}
