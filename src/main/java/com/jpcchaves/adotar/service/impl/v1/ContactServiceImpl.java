package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.Contact;
import com.jpcchaves.adotar.domain.entities.User;
import com.jpcchaves.adotar.exception.ResourceNotFoundException;
import com.jpcchaves.adotar.exception.UnexpectedErrorException;
import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.repository.ContactRepository;
import com.jpcchaves.adotar.repository.UserRepository;
import com.jpcchaves.adotar.service.usecases.v1.ContactService;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;
    private final MapperUtils mapperUtils;

    public ContactServiceImpl(ContactRepository contactRepository,
                              UserRepository userRepository, SecurityContextService securityContextService,
                              MapperUtils mapperUtils) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.securityContextService = securityContextService;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public ContactDto getUserContact() {
        User user = securityContextService.getCurrentLoggedUser();
        User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        return mapperUtils
                .parseObject(
                        currentUser.getContact(),
                        ContactDto.class
                );
    }

    @Override
    public ApiMessageResponseDto createContact(ContactDto contactDto) {
        Contact contact = new Contact(contactDto.getPhone1(), contactDto.getPhone2(), contactDto.getPhone3());
        User user = userRepository.findById(securityContextService.getCurrentLoggedUser().getId()).orElseThrow(() -> new UnexpectedErrorException("Ocorreu um erro inesperado ao criar o contato. Por favor, tente novamente mais tarde"));

        user.setContact(contact);
        userRepository.save(user);

        return new ApiMessageResponseDto("Contato adicionado com sucesso!");
    }

    @Override
    public ContactDto updateUserContact(ContactDto contactDto) {
        User user = securityContextService.getCurrentLoggedUser();
        User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
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
