package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.PetPicture;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.payload.dto.pet.PetPictureDto;
import com.jpcchaves.adotar.repository.PetPictureRepository;
import com.jpcchaves.adotar.service.usecases.PetPictureService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetPictureServiceImpl implements PetPictureService {

    private final PetPictureRepository petPictureRepository;
    private final MapperUtils mapperUtils;

    public PetPictureServiceImpl(PetPictureRepository petPictureRepository,
                                 MapperUtils mapperUtils) {
        this.petPictureRepository = petPictureRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<PetPictureDto> getAll(Long petId) {
        List<PetPicture> petPictures = petPictureRepository.getAllByPet_Id(petId);
        return mapperUtils.parseListObjects(petPictures, PetPictureDto.class);
    }

    @Override
    public PetPictureDto getById(Long petId,
                                 Long picId) {
        PetPicture petPicture = petPictureRepository
                .getByIdAndPet_Id(picId, petId)
                .orElseThrow(() -> new ResourceNotFoundException("Foto não encontrada"));
        return mapperUtils.parseObject(petPicture, PetPictureDto.class);
    }

    @Override
    public PetPictureDto create(Long petId,
                                PetPictureDto petPictureDto) {
        return null;
    }

    @Override
    public PetPictureDto update(Long petId,
                                Long picId,
                                PetPictureDto petPictureDto) {
        return null;
    }

    @Override
    public PetPictureDto delete(Long petId,
                                Long picId) {
        return null;
    }
}
