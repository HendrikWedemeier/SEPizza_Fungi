package com.haw.srs.customerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseById(Long id);
    Optional<Course> findCourseByName(String name);
}
