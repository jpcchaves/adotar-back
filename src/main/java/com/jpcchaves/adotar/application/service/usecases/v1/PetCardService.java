package com.jpcchaves.adotar.application.service.usecases.v1;

public interface PetCardService {
  byte[] generatePetCard(Long petId);

  byte[] generateEmptyCard();
}
