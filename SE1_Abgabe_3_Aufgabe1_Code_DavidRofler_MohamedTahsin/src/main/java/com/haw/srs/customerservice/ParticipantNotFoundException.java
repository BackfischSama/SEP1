package com.haw.srs.customerservice;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper=false)
class ParticipantNotFoundException extends Exception {

    private final Long participantId;

    ParticipantNotFoundException(Long participantNumber) {
        super(String.format("Could not find participant with number %d.", participantNumber));

        this.participantId = participantNumber;
    }
}