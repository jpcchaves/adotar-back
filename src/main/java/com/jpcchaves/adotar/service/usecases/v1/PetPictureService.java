package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;

import java.util.List;

public interface PetPictureService {
    List<PetPictureDto> getAll(Long petId);

    PetPictureDto getById(
            Long petId,
            Long picId);

    PetPictureDto create(
            Long petId,
            PetPictureDto petPictureDto);

    PetPictureDto update(
            Long petId,
            Long picId,
            PetPictureDto petPictureDto);

    ApiMessageResponseDto markAsFavorite(
            Long petIt,
            Long picId);

    ApiMessageResponseDto delete(
            Long petId,
            Long picId);
}
