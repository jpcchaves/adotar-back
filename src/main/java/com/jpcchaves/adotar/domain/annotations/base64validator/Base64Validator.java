package com.jpcchaves.adotar.domain.annotations.base64validator;

import com.jpcchaves.adotar.utils.base64.Base64Utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Base64;
import java.util.regex.Pattern;

public class Base64Validator implements ConstraintValidator<ValidBase64, String> {
    private static final Pattern BASE64_PREFIX_PATTERN = Pattern.compile("^data:image/.*;base64,");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        if (!BASE64_PREFIX_PATTERN.matcher(value).find()) {
            return false;
        }

        try {
            String base64;

            if (Base64Utils.hasBase64Prefix(value)) {
                base64 = Base64Utils.removeBase64Prefix(value);
            } else {
                return false;
            }

            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
