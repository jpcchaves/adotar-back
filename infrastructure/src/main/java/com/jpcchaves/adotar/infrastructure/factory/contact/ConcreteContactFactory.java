package com.jpcchaves.adotar.infrastructure.factory.contact;

import com.jpcchaves.adotar.infrastructure.domain.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ConcreteContactFactory implements ContactFactory {
  @Override
  public Contact createUserEmptyContact() {
    return new Contact("", "", "");
  }
}
