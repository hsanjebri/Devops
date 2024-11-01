package tn.esprit.spring;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServicesImplTest {

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private CourseServicesImpl courseServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllCourses() {
        Course course1 = new Course();
        Course course2 = new Course();
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        List<Course> courses = courseServices.retrieveAllCourses();

        assertNotNull(courses);
        assertEquals(2, courses.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void addCourse() {
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = courseServices.addCourse(course);

        assertNotNull(savedCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        Course updatedCourse = courseServices.updateCourse(course);

        assertNotNull(updatedCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void retrieveCourse() {
        Long courseId = 1L;
        Course course = new Course();
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        Course retrievedCourse = courseServices.retrieveCourse(courseId);

        assertNotNull(retrievedCourse);
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void retrieveCourse_NotFound() {
        Long courseId = 1L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        Course retrievedCourse = courseServices.retrieveCourse(courseId);

        assertNull(retrievedCourse);
        verify(courseRepository, times(1)).findById(courseId);
    }
}

