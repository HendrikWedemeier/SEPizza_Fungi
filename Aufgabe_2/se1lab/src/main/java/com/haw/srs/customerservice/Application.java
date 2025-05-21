package com.haw.srs.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Component
class PopulateTestDataRunner implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public PopulateTestDataRunner(CustomerRepository customerRepository,
                                  CourseRepository courseRepository) {
        this.customerRepository = customerRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) {
        Arrays.asList(
                        "Miller,Doe,Smith".split(","))
                .forEach(
                        name -> customerRepository.save(new Lecturer("Jane", name, Gender.FEMALE, name + "@dummy.org", null))
                );

        Lecturer lecturer = new Lecturer("Stefan", "Sarstedt", Gender.MALE, "stefan.sarstedt@haw-hamburg.de", new PhoneNumber("+49-40-428758434"));
        Course course = new Course("Software Engineering 1");
        lecturer.addCourse(course);
        customerRepository.save(lecturer);
    }
}
