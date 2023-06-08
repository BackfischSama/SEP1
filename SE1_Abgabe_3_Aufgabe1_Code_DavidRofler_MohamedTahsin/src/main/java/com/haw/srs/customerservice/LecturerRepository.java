package com.haw.srs.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    Optional<Lecturer> findByLastName(String lastName);

    Optional<Lecturer> findCustomerByFirstName(String lastName);

}
