package com.jpcchaves.adotar.payload.dto.email;

public class ContactEmailDto {
    private String subject;
    private String message;

    public ContactEmailDto() {
    }

    public ContactEmailDto(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
