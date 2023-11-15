package com.jpcchaves.adotar.service.impl.v2;

import com.jpcchaves.adotar.domain.entities.Pet;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.payload.dto.pet.v2.PetMinDtoV2;
import com.jpcchaves.adotar.repository.PetRepository;
import com.jpcchaves.adotar.repository.UserSavedPetsRepository;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.service.usecases.v2.PetServiceV2;
import com.jpcchaves.adotar.utils.global.GlobalUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceV2Impl implements PetServiceV2 {
    private final PetRepository petRepository;
    private final UserSavedPetsRepository userSavedPetsRepository;
    private final SecurityContextService securityContextService;
    private final GlobalUtils globalUtils;
    private final MapperUtils mapper;

    public PetServiceV2Impl(
            PetRepository petRepository,
            UserSavedPetsRepository userSavedPetsRepository,
            SecurityContextService securityContextService,
            GlobalUtils globalUtils,
            MapperUtils mapper) {
        this.petRepository = petRepository;
        this.userSavedPetsRepository = userSavedPetsRepository;
        this.securityContextService = securityContextService;
        this.globalUtils = globalUtils;
        this.mapper = mapper;
    }

    @Override
    @Cacheable("pets")
    public ApiResponsePaginatedDto<PetMinDtoV2> listAllV2(Pageable pageable) {
        User user = securityContextService.getCurrentLoggedUser();
        Page<Pet> petPage = petRepository.findAll(pageable);
        List<PetMinDtoV2> petDtoList = mapper.parseListObjects(petPage.getContent(), PetMinDtoV2.class);

        markFavoritePets(user, petDtoList);

        return globalUtils.buildApiResponsePaginated(petPage, petDtoList);
    }

    private void markFavoritePets(
            User user,
            List<PetMinDtoV2> petDtoList) {
        for (PetMinDtoV2 petDto : petDtoList) {
            if (isPetSavedByUser(user.getId(), petDto.getId())) {
                petDto.setFavorite(true);
            }
        }
    }

    private boolean isPetSavedByUser(
            Long userId,
            Long petId) {
        return userSavedPetsRepository.existsByPet_IdAndUser_Id(petId, userId);
    }
}
