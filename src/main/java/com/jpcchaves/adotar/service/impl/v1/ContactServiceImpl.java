package com.jpcchaves.adotar.service.impl.v1;

import com.jpcchaves.adotar.domain.entities.Contact;
import com.jpcchaves.adotar.payload.dto.contact.ContactDto;
import com.jpcchaves.adotar.repository.ContactRepository;
import com.jpcchaves.adotar.service.usecases.v1.ContactService;
import com.jpcchaves.adotar.service.usecases.v1.SecurityContextService;
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

    // todo: implement create method

    @Override
    public ContactDto updateUserContact(ContactDto contactDto) {
        Contact contact = securityContextService.getCurrentLoggedUser().getContact();

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
