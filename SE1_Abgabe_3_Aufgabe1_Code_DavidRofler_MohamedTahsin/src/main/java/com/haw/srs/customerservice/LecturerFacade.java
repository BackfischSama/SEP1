package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class LecturerFacade {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerFacade(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @GetMapping
    public List<Lecturer> getCustomers() {
        return lecturerRepository.findAll();
    }

    @GetMapping(value = "/{id:[\\d]+}")
    public Lecturer getCustomer(@PathVariable("id") Long customerId) throws LecturerNotFoundException {
        return lecturerRepository
                .findById(customerId)
                .orElseThrow(() -> new LecturerNotFoundException(customerId));
    }

    @DeleteMapping("/{id:[\\d]+}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") Long customerId) throws LecturerNotFoundException {
        Lecturer lecturer = lecturerRepository
                .findById(customerId)
                .orElseThrow(() -> new LecturerNotFoundException(customerId));

        lecturerRepository.delete(lecturer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lecturer createCustomer(@RequestBody Lecturer lecturer) {

        return lecturerRepository.save(lecturer);
    }

    @PutMapping
    public Lecturer updateCustomer(@RequestBody Lecturer lecturer) throws LecturerNotFoundException {
        Lecturer lecturerToUpdate = lecturerRepository
                .findById(lecturer.getId())
                .orElseThrow(() -> new LecturerNotFoundException(lecturer.getId()));

        return lecturerRepository.save(lecturer);
    }
}
