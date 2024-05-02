package com.jpcchaves.adotar.infrastructure.application.service.contact;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.contact.ContactDto;
import com.jpcchaves.adotar.infrastructure.application.service.auth.contracts.SecurityContextService;
import com.jpcchaves.adotar.infrastructure.application.service.contact.contracts.ContactService;
import com.jpcchaves.adotar.infrastructure.application.utils.mapper.MapperUtils;
import com.jpcchaves.adotar.infrastructure.domain.exception.BadRequestException;
import com.jpcchaves.adotar.infrastructure.domain.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.infrastructure.domain.exception.UnexpectedErrorException;
import com.jpcchaves.adotar.infrastructure.domain.model.Contact;
import com.jpcchaves.adotar.infrastructure.domain.model.User;
import com.jpcchaves.adotar.infrastructure.infra.repository.ContactRepository;
import com.jpcchaves.adotar.infrastructure.infra.repository.UserRepository;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
  private final ContactRepository contactRepository;
  private final UserRepository userRepository;
  private final SecurityContextService securityContextService;
  private final MapperUtils mapperUtils;

  public ContactServiceImpl(
      ContactRepository contactRepository,
      UserRepository userRepository,
      SecurityContextService securityContextService,
      MapperUtils mapperUtils) {
    this.contactRepository = contactRepository;
    this.userRepository = userRepository;
    this.securityContextService = securityContextService;
    this.mapperUtils = mapperUtils;
  }

  @Override
  public ContactDto getUserContact() {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found!"));

    return mapperUtils.parseObject(currentUser.getContact(), ContactDto.class);
  }

  @Override
  public ApiMessageResponseDto createContact(ContactDto contactDto) {
    Contact contact =
        new Contact(
            contactDto.getPhone1(),
            contactDto.getPhone2(),
            contactDto.getPhone3());
    User user =
        userRepository
            .findById(securityContextService.getCurrentLoggedUser().getId())
            .orElseThrow(
                () ->
                    new UnexpectedErrorException(
                        "Ocorreu um erro inesperado ao criar o contato. Por favor, tente novamente mais tarde"));

    if (!Objects.isNull(user.getContact())) {
      throw new BadRequestException(
          "O usuario ja possui um contato cadastrado!");
    }

    user.setContact(contact);
    userRepository.save(user);

    return new ApiMessageResponseDto("Contato adicionado com sucesso!");
  }

  @Override
  public ContactDto updateUserContact(ContactDto contactDto) {
    User user = securityContextService.getCurrentLoggedUser();
    User currentUser =
        userRepository
            .findById(user.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found!"));
    Contact contact = currentUser.getContact();

    updateContact(contact, contactDto);

    Contact updatedContact = contactRepository.save(contact);

    return mapperUtils.parseObject(updatedContact, ContactDto.class);
  }

  private void updateContact(Contact contact, ContactDto contactDto) {
    contact.setPhone1(contactDto.getPhone1());
    contact.setPhone2(contactDto.getPhone2());
    contact.setPhone3(contactDto.getPhone3());
  }
}
