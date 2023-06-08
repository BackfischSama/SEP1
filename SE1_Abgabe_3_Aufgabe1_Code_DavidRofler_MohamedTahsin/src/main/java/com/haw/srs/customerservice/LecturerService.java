package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    private final ParticipantRepository participantRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public LecturerService(LecturerRepository lecturerRepository, ParticipantRepository participantRepository, CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.participantRepository = participantRepository;
        this.courseRepository = courseRepository;
    }


    public List<Lecturer> findAllLecturers() {
        return lecturerRepository.findAll();
    }

    public List<Participant> findAllParticipants() {
        return participantRepository.findAll();
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Lecturer findLecturerByLastname(String lastName) throws LecturerNotFoundException {
        return lecturerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new LecturerNotFoundException(lastName));
    }

    public Lecturer createLecturer(String firstName, String lastName, Gender gender) throws LecturerAlreadyExistingException {
        if (lecturerRepository.findByLastName(lastName).isPresent()) {
            throw new LecturerAlreadyExistingException(lastName);
        }
        return lecturerRepository.save(new Lecturer(firstName, lastName, gender));
    }

    public Long createParticipant(String name) throws ParticipantAlreadyExistingException {
        // Falls ein Teilnehmer mit dem angegebenen Namen bereits existiert -> Exception
        if (participantRepository.findByName(name).isPresent()) {
            throw new ParticipantAlreadyExistingException(name);
        }
        // Neuer Teilnehmer wird erstellt und in der Teilnehmer-Repository gespeichert, ID des Teilnehmers wird zurückgegeben
        return participantRepository.save(new Participant(name)).getId();
    }

    public List<Participant> enrollParticipant(Long courseId, Long participantId) throws CourseNotFoundException, ParticipantNotFoundException {
        // Die Kurs-Repository wird nach dem Kurs mit der angegebenen ID durchsucht, Ergebnis in einem Optional-Objekt gespeichert.
        Optional<Course> course = courseRepository.findById(courseId);

        // Teilnehmer mit der angegebenen ID vorhanden?
        if (participantRepository.findById(participantId).isPresent()) {
            // Kurs mit der angegebenen ID vorhanden?
            if (course.isPresent()) {
                course.get().addParticipant(participantRepository.findById(participantId).get());
            } else {
                throw new CourseNotFoundException(courseId); // Kurs nicht gefunden? -> Exception
            }
        } else {
            throw new ParticipantNotFoundException(participantId); // Teilnehmer nicht gefunden? -> Exception
        }
        return course.get().getParticipants(); //Liste Teilnehmer
    }
}
