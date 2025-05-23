package com.haw.srs.customerservice;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class CourseNotFoundException extends Exception {

    int courseNumber;

    CourseNotFoundException(int courseNumber) {
        super(String.format("Could not find course with number %d.", courseNumber));

        this.courseNumber = courseNumber;
    }
}