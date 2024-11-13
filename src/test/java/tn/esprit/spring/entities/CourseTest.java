package tn.esprit.spring.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void getNumCourse() {
        Course course = new Course();
        course.setNumCourse(1L);
        assertEquals(1L, course.getNumCourse());
    }

    @Test
    void getLevel() {
        Course course = new Course();
        course.setLevel(3);
        assertEquals(3, course.getLevel());
    }

    @Test
    void getTypeCourse() {
        Course course = new Course();
        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        assertEquals(TypeCourse.INDIVIDUAL, course.getTypeCourse());
    }

    @Test
    void getSupport() {
        Course course = new Course();
        course.setSupport(Support.SNOWBOARD);
        assertEquals(Support.SNOWBOARD, course.getSupport());
    }

    @Test
    void getPrice() {
        Course course = new Course();
        course.setPrice(100.0f);
        assertEquals(100.0f, course.getPrice());
    }

    @Test
    void getTimeSlot() {
        Course course = new Course();
        course.setTimeSlot(2);
        assertEquals(2, course.getTimeSlot());
    }



    // Additional setter tests as needed
}