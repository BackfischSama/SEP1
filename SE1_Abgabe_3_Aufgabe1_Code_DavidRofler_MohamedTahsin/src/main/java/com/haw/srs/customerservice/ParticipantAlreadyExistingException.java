package com.haw.srs.customerservice;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.BAD_REQUEST)
class ParticipantAlreadyExistingException extends Exception {

    private final String name;

    ParticipantAlreadyExistingException(String name) {
        super(String.format("Participant with name %s does already exist.", name));
        this.name = name;
    }
}