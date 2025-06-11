package com.haw.srs.customerservice;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
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
    private CustomerRepository customerRepository;

    @MockitoBean
    private MailGateway mailGateway;
    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
    }

    @Test
    void enrollCustomerInCourseSuccess() throws CustomerNotFoundException {
        Lecturer lecturer = new Lecturer("Jane", "Doe", Gender.FEMALE, "jane.doe@mail.com", null);
        customerRepository.save(lecturer);

        assertThat(lecturer.getCourses()).size().isEqualTo(0);

        courseService.enrollInCourse(lecturer.getLastName(), new Course("Software Engineering 1"));

        assertThat(lecturerService.findCustomerByLastname(lecturer.getLastName()).getCourses())
                .size().isEqualTo(1);
    }

    @Test
    void enrollCustomerInCourseFailBecauseOfCustomerNotFound() {
        assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> courseService.enrollInCourse("notExisting", new Course("Software Engineering 1")))
                .withMessageContaining("Could not find customer with lastname notExisting.");
    }

    @Test
    @Transactional
    void transferCoursesSuccess() throws CustomerNotFoundException {
        Lecturer from = new Lecturer("John", "Smith", Gender.MALE);
        from.addCourse(new Course("Software Engineering 1"));
        from.addCourse(new Course("Software Engineering 2"));
        customerRepository.save(from);
        Lecturer to = new Lecturer("Eva", "Miller", Gender.FEMALE);
        customerRepository.save(to);

        assertThat(from.getCourses()).size().isEqualTo(2);
        assertThat(to.getCourses()).size().isEqualTo(0);

        courseService.transferCourses(from.getLastName(), to.getLastName());

        Lecturer cUpdated = customerRepository.findById(from.getId()).get();

        assertThat(lecturerService.findCustomerByLastname(from.getLastName()).getCourses())
                .size().isEqualTo(0);
        assertThat(lecturerService.findCustomerByLastname(to.getLastName()).getCourses())
                .size().isEqualTo(2);
    }

    @Test
    void cancelMembershipSuccess() throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSent {
        // set up customer and course here
        // ...

        // configure MailGateway-mock
        when(mailGateway.sendMail(anyString(), anyString(), anyString())).thenReturn(true);

        courseService.cancelMembership(1L, 1L);
    }

    @Test
    void cancelMembershipFailBecauseOfUnableToSendMail() {
        // set up customer and course here
        // ...

        // configure MailGateway-mock
        when(mailGateway.sendMail(anyString(), anyString(), anyString())).thenReturn(false);

        assertThatExceptionOfType(MembershipMailNotSent.class)
                .isThrownBy(() -> courseService.cancelMembership(1L, 1L))
                .withMessageContaining("Could not send membership mail to");
    }

    @Test
    void cancelMembershipSuccessBDDStyle() throws CustomerNotFoundException, CourseNotFoundException, MembershipMailNotSent {
        // set up customer and course here
        // ...

        // configure MailGateway-mock with BDD-style
        given(mailGateway.sendMail(anyString(), anyString(), anyString())).willReturn(true);

        courseService.cancelMembership(1L, 1L);
    }
}