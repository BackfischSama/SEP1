package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LecturerRepositoryTest {

    @Autowired
    private LecturerRepository lecturerRepository;

    @BeforeEach
    void setUp() {
        this.lecturerRepository.deleteAll();

        Lecturer lecturer = this.lecturerRepository.save(new Lecturer("Stefan", "Sarstedt", Gender.MALE,
                "stefan.sarstedt@haw-hamburg.de",
                new PhoneNumber("+49", "040", "428758434")));
    }

    @Test
    void findCustomerByLastNameSuccess() {
        assertThat(lecturerRepository.findByLastName("Sarstedt").isPresent()).isTrue();
    }

    @Test
    void findCustomerByLastNameFail() {
        assertThat(lecturerRepository.findByLastName("notExisting").isPresent()).isFalse();
    }
}
