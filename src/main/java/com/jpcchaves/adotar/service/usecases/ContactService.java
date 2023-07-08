package com.jpcchaves.adotar.service.usecases;

import com.jpcchaves.adotar.payload.dto.contact.ContactDto;

public interface ContactService {
    ContactDto getUserContact();

    ContactDto updateUserContact(ContactDto addressDto);
}
