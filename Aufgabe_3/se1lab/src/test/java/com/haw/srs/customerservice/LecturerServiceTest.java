package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LecturerServiceTest {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
        courseRepository.deleteAll();
        participantRepository.deleteAll();
    }

    @Test
    void getAllCustomersSuccess() {
        assertThat(lecturerService.findAllCustomers()).size().isEqualTo(0);

        Lecturer lecturer = new Lecturer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        customerRepository.save(lecturer);

        List<Lecturer> actual = lecturerService.findAllCustomers();
        assertThat(actual).size().isEqualTo(1);
        assertThat(actual.get(0).getFirstName()).isEqualTo("Jane");
    }

    @Test
    void saveParticipant() throws DuplicateFirstNameException {
        assertThat(participantRepository.findAll()).size().isEqualTo(0);

        Participant participant = new Participant("Kevin");
        participantRepository.save(participant);

        LecturerService service = new LecturerService(customerRepository, participantRepository, courseRepository);

        Participant participantFromDB = participantRepository.findById(participant.getId()).get();
        assertThat(participant.getId()).isEqualTo(participantFromDB.getId());
        assertThat(participant.getName()).isEqualTo(participantFromDB.getName());

        service.createParticipant("Kevin1");
        assertThat(participantRepository.findByName("Kevin1").get().getName()).isEqualTo("Kevin1");
        assertThatExceptionOfType(DuplicateFirstNameException.class)
                .isThrownBy(() -> service.createParticipant("Kevin1"));
    }

    @Test
    void testEnrollParticipant() throws CourseNotFoundException, ParticipantNotFoundException {
        LecturerService service = new LecturerService(customerRepository, participantRepository, courseRepository);

        Course course = new Course("DB");
        Participant parti = new Participant("John Software");
        participantRepository.save(parti);
        courseRepository.save(course);

        assertThatExceptionOfType(CourseNotFoundException.class)
                .isThrownBy(() -> service.enrollParticipant(course.getId() + 1, parti.getId()));

        assertThatExceptionOfType(ParticipantNotFoundException.class)
                .isThrownBy(() -> service.enrollParticipant(course.getId(), parti.getId() + 1));

        Course dbCourse = courseRepository.findCourseByName(course.getName()).get();
        Participant dvParti = participantRepository.findByName("John Software").get();
        lecturerService.enrollParticipant(dbCourse.getId(), dvParti.getId());

    }
}