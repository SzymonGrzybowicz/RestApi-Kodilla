package com.crud.tasks.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mail {

    private String mailTo;
    private String subject;
    private String message;
    private String toCC;

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToCC() {
        return toCC;
    }
}
