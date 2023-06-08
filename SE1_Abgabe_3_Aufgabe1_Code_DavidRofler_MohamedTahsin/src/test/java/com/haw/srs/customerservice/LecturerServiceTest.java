package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LecturerServiceTest {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setup() {
        lecturerRepository.deleteAll();
        courseRepository.deleteAll();
        participantRepository.deleteAll();
    }

    @Test
    void getAllLecturersSuccess() {
        assertThat(lecturerService.findAllLecturers()).size().isEqualTo(0);

        Lecturer lecturer = new Lecturer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        lecturerRepository.save(lecturer);

        List<Lecturer> actual = lecturerService.findAllLecturers();
        assertThat(actual).size().isEqualTo(1);
        assertThat(actual.get(0).getFirstName()).isEqualTo("Jane");
    }

    /**
     * Testet b der Teilnehmer erfolgreich erstellt wurde und ob die
     * entsprechenden Datenbankaktualisierungen durchgeführt wurden.
     *
     * @throws ParticipantAlreadyExistingException wenn ein Teilnehmer bereits existiert.
     */
    @Test
    void createParticipantSuccess() throws ParticipantAlreadyExistingException {
        assertThat(lecturerService.findAllParticipants()).size().isEqualTo(0);

        Long id = lecturerService.createParticipant("Thomas");

        assertThat(participantRepository.findByName("Thomas").get().getId()).isEqualTo(id);
    }

    /**
     * Testet, ob versucht wird, einen Teilnehmer zu erstellen, der bereits existiert.
     *
     * @throws ParticipantAlreadyExistingException wenn ein Teilnehmer bereits existiert.
     */
    @Test
    void createParticipantFailure() throws ParticipantAlreadyExistingException {
        assertThat(lecturerService.findAllParticipants()).size().isEqualTo(0);
        lecturerService.createParticipant("Thomas");
        assertThatExceptionOfType(ParticipantAlreadyExistingException.class).isThrownBy(() -> lecturerService.createParticipant("Thomas"));
    }

    /**
     * Testet ob der Teilnehmer erfolgreich in den Kurs eingeschrieben wurde und
     * ob die entsprechenden Datenbankaktualisierungen durchgeführt wurden
     *
     * @throws CourseNotFoundException      wenn der Kurs nicht gefunden wird.
     * @throws ParticipantNotFoundException wenn der Teilnehmer nicht gefunden wird.
     */
    @Test
    void enrollParticipantSuccess() throws CourseNotFoundException, ParticipantNotFoundException {
        assertThat(lecturerService.findAllCourses()).size().isEqualTo(0);
        assertThat(lecturerService.findAllParticipants()).size().isEqualTo(0);

        Course course = new Course("Software Engineering 1");
        courseRepository.save(course);

        Participant participant = new Participant("David");
        participantRepository.save(participant);

        assertThat(lecturerService.enrollParticipant(
                courseRepository.findByName(course.getName()).get().getId(),
                participantRepository.findByName(participant.getName()).get().getId()))
                .size().isEqualTo(1);
    }

    /**
     * Testet, ob versucht wird, einen Teilnehmer in einen nicht vorhandenen Kurs einzuschreiben.
     */
    @Test
    void enrollParticipantFailureCourse() {
        assertThat(lecturerService.findAllCourses()).size().isEqualTo(0);
        assertThat(lecturerService.findAllParticipants()).size().isEqualTo(0);

        Participant participant = new Participant("David");
        participantRepository.save(participant);

        assertThatExceptionOfType(CourseNotFoundException.class).isThrownBy(
                () -> lecturerService.enrollParticipant(15L,
                        participantRepository.findByName(participant.getName()).get().getId()));
    }

    /**
     * Testet, ob versucht wird, einen nicht vorhandenen Teilnehmer in einen Kurs einzuschreiben.
     * ID 1L nicht vorhanden
     */
    @Test
    void enrollParticipantFailureParticipant() {
        assertThat(lecturerService.findAllCourses()).size().isEqualTo(0);
        assertThat(lecturerService.findAllParticipants()).size().isEqualTo(0);

        Course course = new Course("Software Engineering 1");
        courseRepository.save(course);

        assertThatExceptionOfType(ParticipantNotFoundException.class).isThrownBy(
                () -> lecturerService.enrollParticipant(
                        courseRepository.findByName(course.getName()).get().getId(), 1L));
    }
}