package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.domain.entities.PetPicture;
import com.jpcchaves.adotar.exception.BadRequestException;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.repository.PetPictureRepository;
import com.jpcchaves.adotar.repository.PetRepository;
import com.jpcchaves.adotar.service.usecases.PetPictureService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetPictureServiceImpl implements PetPictureService {

    private final PetPictureRepository petPictureRepository;
    private final PetRepository petRepository;
    private final MapperUtils mapperUtils;

    public PetPictureServiceImpl(
            PetPictureRepository petPictureRepository,
            PetRepository petRepository,
            MapperUtils mapperUtils) {
        this.petPictureRepository = petPictureRepository;
        this.petRepository = petRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<PetPictureDto> getAll(Long petId) {
        List<PetPicture> petPictures = fetchPetPictures(petId);
        return mapperUtils.parseListObjects(petPictures, PetPictureDto.class);
    }

    @Override
    public PetPictureDto getById(
            Long petId,
            Long picId) {
        PetPicture petPicture = fetchPetPictureById(picId, petId);
        return mapperUtils.parseObject(petPicture, PetPictureDto.class);
    }

    @Override
    public PetPictureDto create(
            Long petId,
            PetPictureDto petPictureDto) {

        Pet pet = fetchPet(petId);

        verifyPicturesLimit(pet.getPetPictures());

        PetPicture petPicture = mapperUtils.parseObject(petPictureDto, PetPicture.class);
        petPicture.setPet(pet);

        PetPicture savedPetPicture = petPictureRepository.save(petPicture);
        return mapperUtils.parseObject(savedPetPicture, PetPictureDto.class);
    }

    @Override
    public PetPictureDto update(
            Long petId,
            Long picId,
            PetPictureDto petPictureDto) {

        PetPicture petPicture = fetchPetPictureById(picId, petId);

        petPicture.setImgUrl(petPictureDto.getImgUrl());

        PetPicture updatedPicture = petPictureRepository.save(petPicture);
        return mapperUtils.parseObject(updatedPicture, PetPictureDto.class);
    }

    @Override
    public ApiMessageResponseDto delete(
            Long petId,
            Long picId) {
        PetPicture petPicture = fetchPetPictureById(picId, petId);

        petPictureRepository.deleteById(petPicture.getId());

        return new ApiMessageResponseDto("Foto excluída com sucesso");
    }

    private PetPicture fetchPetPictureById(
            Long picId,
            Long petId) {
        return petPictureRepository
                .getByIdAndPet_Id(picId, petId)
                .orElseThrow(() -> new ResourceNotFoundException("Foto não encontrada"));
    }

    private Pet fetchPet(Long petId) {
        return petRepository
                .findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com o id informado: " + petId));
    }

    private List<PetPicture> fetchPetPictures(Long petId) {
        return petPictureRepository.getAllByPet_Id(petId);
    }

    private boolean isUnderLimit(List<PetPicture> pictures) {
        final int LIMIT = 6;
        return pictures.size() < LIMIT;
    }

    private void verifyPicturesLimit(List<PetPicture> pictures) {
        if (!isUnderLimit(pictures)) {
            throw new BadRequestException("O limite de fotos foi excedido!");
        }
    }

}
