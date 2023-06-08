package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MailGateway mailGateway;

    @Transactional
    public void enrollInCourse(String lastName, Course course) throws LecturerNotFoundException {
        Lecturer lecturer = lecturerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new LecturerNotFoundException(lastName));

        lecturer.addCourse(course);
        lecturerRepository.save(lecturer);
    }

    @Transactional
    public void transferCourses(String fromCustomerLastName, String toCustomerLastName) throws LecturerNotFoundException {
        Lecturer from = lecturerRepository
                .findByLastName(fromCustomerLastName)
                .orElseThrow(() -> new LecturerNotFoundException(fromCustomerLastName));
        Lecturer to = lecturerRepository
                .findByLastName(toCustomerLastName)
                .orElseThrow(() -> new LecturerNotFoundException(toCustomerLastName));

        to.getCourses().addAll(from.getCourses());
        from.getCourses().clear();

        lecturerRepository.save(from);
        lecturerRepository.save(to);
    }

    /**
     * Cancels a course membership. An Email is sent to all possible participants on the waiting list for this course.
     * If customer is not member of the provided course, the operation is ignored.
     *
     * @throws IllegalArgumentException if customerNumber==null or courseNumber==null
     */
    @Transactional
    public void cancelMembership(LecturerNumber lecturerNumber, CourseNumber courseNumber) throws LecturerNotFoundException, CourseNotFoundException, MembershipMailNotSent {

        // some implementation goes here
        // find customer, find course, look for membership, remove membership, etc.
        String customerMail = "customer@domain.com";

        boolean mailWasSent = mailGateway.sendMail(customerMail, "Oh, we're sorry that you canceled your membership!", "Some text to make her/him come back again...");
        if (!mailWasSent) {
            // do some error handling here (including e.g. transaction rollback, etc.)
            // ...
            
            throw new MembershipMailNotSent(customerMail);
        }
    }
}
