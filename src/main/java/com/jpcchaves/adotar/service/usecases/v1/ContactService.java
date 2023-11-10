package com.jpcchaves.adotar.service.usecases.v1;

import com.jpcchaves.adotar.payload.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.payload.dto.contact.ContactDto;

public interface ContactService {
    ContactDto getUserContact();

    ApiMessageResponseDto createContact(ContactDto contactDto);

    ContactDto updateUserContact(ContactDto contactDto);
}
