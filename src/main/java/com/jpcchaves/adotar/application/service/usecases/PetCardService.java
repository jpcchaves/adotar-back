package com.jpcchaves.adotar.application.service.usecases;

public interface PetCardService {
    byte[] generatePetCard(Long petId);

    byte[] generateEmptyCard();
}
