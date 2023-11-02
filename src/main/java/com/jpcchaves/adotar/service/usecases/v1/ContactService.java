package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.contact.ContactDto;

public interface ContactService {
    ContactDto getUserContact();

    ContactDto updateUserContact(ContactDto contactDto);
}
