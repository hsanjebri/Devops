package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServicesImplTest {

    @Mock
    ICourseRepository courseRepository;

    @InjectMocks
    CourseServicesImpl courseServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllCourses() {
        List<Course> courses = Collections.singletonList(new Course());
        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseServices.retrieveAllCourses();

        assertEquals(courses, result);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void addCourse() {
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseServices.addCourse(course);

        assertEquals(course, result);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseServices.updateCourse(course);

        assertEquals(course, result);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void retrieveCourse() {
        Course course = new Course();
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course result = courseServices.retrieveCourse(1L);

        assertEquals(course, result);
        verify(courseRepository, times(1)).findById(1L);
    }
}