package com.jpcchaves.adotar.infrastructure.application.service.contact.contracts;

import com.jpcchaves.adotar.infrastructure.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.infrastructure.application.dto.contact.ContactDto;

public interface ContactService {
  ContactDto getUserContact();

  ApiMessageResponseDto createContact(ContactDto contactDto);

  ContactDto updateUserContact(ContactDto contactDto);
}
