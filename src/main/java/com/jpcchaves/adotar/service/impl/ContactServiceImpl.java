package com.jpcchaves.adotar.service.impl;

import com.jpcchaves.adotar.domain.entities.Contact;
import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.repository.ContactRepository;
import com.jpcchaves.adotar.service.usecases.ContactService;
import com.jpcchaves.adotar.service.usecases.SecurityContextService;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final SecurityContextService securityContextService;
    private final MapperUtils mapperUtils;

    public ContactServiceImpl(ContactRepository contactRepository,
                              SecurityContextService securityContextService,
                              MapperUtils mapperUtils) {
        this.contactRepository = contactRepository;
        this.securityContextService = securityContextService;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public ContactDto getUserContact() {
        return mapperUtils
                .parseObject(
                        securityContextService.getCurrentLoggedUser().getContact(),
                        ContactDto.class
                );
    }

    @Override
    public ContactDto updateUserContact(ContactDto addressDto) {
        Contact contact = securityContextService.getCurrentLoggedUser().getContact();

        contact.setPhone1(addressDto.getPhone1());
        contact.setPhone2(addressDto.getPhone2());
        contact.setPhone3(addressDto.getPhone3());

        Contact updatedContact = contactRepository.save(contact);

        return mapperUtils.parseObject(updatedContact, ContactDto.class);
    }
}
