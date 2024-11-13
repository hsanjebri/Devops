package tn.esprit.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.services.ICourseServices;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseRestControllerTest {

    @Mock
    ICourseServices courseServices;

    @InjectMocks
    CourseRestController courseRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCourse() {
        Course course = new Course();
        when(courseServices.addCourse(course)).thenReturn(course);

        Course result = courseRestController.addCourse(course);

        assertEquals(course, result);
        verify(courseServices, times(1)).addCourse(course);
    }

    @Test
    void getAllCourses() {
        List<Course> courses = Collections.singletonList(new Course());
        when(courseServices.retrieveAllCourses()).thenReturn(courses);

        List<Course> result = courseRestController.getAllCourses();

        assertEquals(courses, result);
        verify(courseServices, times(1)).retrieveAllCourses();
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        when(courseServices.updateCourse(course)).thenReturn(course);

        Course result = courseRestController.updateCourse(course);

        assertEquals(course, result);
        verify(courseServices, times(1)).updateCourse(course);
    }

    @Test
    void getById() {
        Course course = new Course();
        when(courseServices.retrieveCourse(1L)).thenReturn(course);

        Course result = courseRestController.getById(1L);

        assertEquals(course, result);
        verify(courseServices, times(1)).retrieveCourse(1L);
    }
}