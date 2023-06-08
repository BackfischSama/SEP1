package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CourseServiceTest {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LecturerRepository lecturerRepository;

    @MockBean
    private MailGateway mailGateway;

    @BeforeEach
    void setup() {
        lecturerRepository.deleteAll();
    }



    @Test
    void enrollCustomerInCourseSuccess() throws LecturerNotFoundException {
        Lecturer lecturer = new Lecturer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        lecturerRepository.save(lecturer);

        assertThat(lecturer.getCourses()).size().isEqualTo(0);

        courseService.enrollInCourse(lecturer.getLastName(), new Course("Software Engineering 1"));

        assertThat(lecturerService.findLecturerByLastname(lecturer.getLastName()).getCourses())
                .size().isEqualTo(1);
    }


    @Test
    void enrollCustomerInCourseFailBecauseOfCustomerNotFound() {
        assertThatExceptionOfType(LecturerNotFoundException.class)
                .isThrownBy(() -> courseService.enrollInCourse("notExisting", new Course("Software Engineering 1")))
                .withMessageContaining("Could not find customer with lastname notExisting.");
    }


    @Test
    void transferCoursesSuccess() throws LecturerNotFoundException {
        Lecturer from = new Lecturer("John", "Smith", Gender.MALE);
        from.addCourse(new Course("Software Engineering 1"));
        from.addCourse(new Course("Software Engineering 2"));
        lecturerRepository.save(from);
        Lecturer to = new Lecturer("Eva", "Miller", Gender.FEMALE);
        lecturerRepository.save(to);

        assertThat(from.getCourses()).size().isEqualTo(2);
        assertThat(to.getCourses()).size().isEqualTo(0);

        courseService.transferCourses(from.getLastName(), to.getLastName());

        assertThat(lecturerService.findLecturerByLastname(from.getLastName()).getCourses())
                .size().isEqualTo(0);
        assertThat(lecturerService.findLecturerByLastname(to.getLastName()).getCourses())
                .size().isEqualTo(2);
    }



    @Test
    void cancelMembershipSuccess() throws LecturerNotFoundException, CourseNotFoundException, MembershipMailNotSent {

        when(mailGateway.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        courseService.cancelMembership(new LecturerNumber(1L), new CourseNumber(1L));
    }


    @Test
    void cancelMembershipFailBecauseOfUnableToSendMail() {

        when(mailGateway.sendMail(anyString(), anyString(), anyString())).thenReturn(false);

        assertThatExceptionOfType(MembershipMailNotSent.class)
                .isThrownBy(() -> courseService.cancelMembership(new LecturerNumber(1L), new CourseNumber(1L)))
                .withMessageContaining("Could not send membership mail to");
    }


    @Test
    void cancelMembershipSuccessBDDStyle() throws LecturerNotFoundException, CourseNotFoundException, MembershipMailNotSent {

        given(mailGateway.sendMail(anyString(), anyString(), anyString())).willReturn(true);

        courseService.cancelMembership(new LecturerNumber(1L), new CourseNumber(1L));
    }
}