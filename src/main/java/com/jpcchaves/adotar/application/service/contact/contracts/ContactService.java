package com.jpcchaves.adotar.application.service.contact.contracts;

import com.jpcchaves.adotar.application.dto.ApiMessageResponseDto;
import com.jpcchaves.adotar.application.dto.contact.ContactDto;

public interface ContactService {
  ContactDto getUserContact();

  ApiMessageResponseDto createContact(ContactDto contactDto);

  ContactDto updateUserContact(ContactDto contactDto);
}
