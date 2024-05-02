package com.jpcchaves.adotar.infrastructure.application.service.pet.contracts;

public interface PetCardService {
  byte[] generatePetCard(Long petId);

  byte[] generateEmptyCard();
}
