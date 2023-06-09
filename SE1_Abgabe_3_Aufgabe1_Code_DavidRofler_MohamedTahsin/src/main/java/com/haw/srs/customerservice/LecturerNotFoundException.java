package com.haw.srs.customerservice;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Value
@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.NOT_FOUND)
class LecturerNotFoundException extends Exception {

    private final Long customerId;
    private final String lastName;

    LecturerNotFoundException(Long customerId) {
        super(String.format("Could not find customer with number %d.", customerId));

        this.customerId = customerId;
        this.lastName = "";
    }

    LecturerNotFoundException(String lastName) {
        super(String.format("Could not find customer with lastname %s.", lastName));

        this.customerId = 0L;
        this.lastName = lastName;
    }
}