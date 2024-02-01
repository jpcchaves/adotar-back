package com.jpcchaves.adotar.factory.contact;

import com.jpcchaves.adotar.domain.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ConcreteContactFactory implements ContactFactory {
    @Override
    public Contact createUserEmptyContact() {
        return new Contact("", "", "");
    }
}
