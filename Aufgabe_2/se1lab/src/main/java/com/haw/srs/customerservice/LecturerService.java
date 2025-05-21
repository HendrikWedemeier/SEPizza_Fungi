package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {

    private final CustomerRepository customerRepository;
    private final ParticipantRepository participantRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LecturerService(CustomerRepository customerRepository, ParticipantRepository participantRepository, CourseRepository courseRepository) {
        this.customerRepository = customerRepository;
        this.participantRepository = participantRepository;
        this.courseRepository = courseRepository;
    }

    public List<Lecturer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Lecturer findCustomerByLastname(String lastName) throws CustomerNotFoundException {
        return customerRepository
                .findByLastName(lastName)
                .orElseThrow(() -> new CustomerNotFoundException(lastName));
    }

    public Lecturer createCustomer(String firstName, String lastName, Gender gender) throws CustomerAlreadyExistingException {
        if (customerRepository.findByLastName(lastName).isPresent()) {
            throw new CustomerAlreadyExistingException(lastName);
        }

        return customerRepository.save(new Lecturer(firstName, lastName, gender));
    }

    public Long createParticipant(String name) throws DuplicateFirstNameException {

        if(participantRepository.findByName(name).isPresent()){
            throw new DuplicateFirstNameException(name);
        }

        Participant newParticipant = new Participant(name);

        return participantRepository.save(newParticipant).getId();
    }

    public void enrollParticipant(Long courseId, Long participantId) throws CourseNotFoundException, ParticipantNotFoundException {
        if(courseRepository.findCourseById(courseId).isEmpty()){
            throw new CourseNotFoundException(courseId.intValue());
        }

        if(participantRepository.findById(participantId).isEmpty()){
            throw new ParticipantNotFoundException(participantId);
        }

        Course course = courseRepository.findCourseById(courseId).get();

        course.getParticipants().add(participantRepository.findById(participantId).get());

        courseRepository.save(course);
    }
}
