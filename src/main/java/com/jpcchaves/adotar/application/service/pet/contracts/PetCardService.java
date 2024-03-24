package com.jpcchaves.adotar.application.service.pet.contracts;

public interface PetCardService {
  byte[] generatePetCard(Long petId);

  byte[] generateEmptyCard();
}
