package com.example.learningapp.service;

import com.example.learningapp.entity.Course;
import com.example.learningapp.entity.User;
import com.example.learningapp.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public Course createCourse(User user, Course course) {

        course.setAuthor(user);
        log.info("Course created");

        return courseRepository.save(course);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
    }

    public void updateCourse(Long id, Course updatedCourse) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            course.setTitle(updatedCourse.getTitle());
            course.setCategory(updatedCourse.getCategory());

            courseRepository.save(course);
        } else {
            throw new IllegalArgumentException("Course not found with id: " + id);
        }
    }

    public void deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Course not found with id: " + id);
        }
    }

    public Course findById(Course courseId) {
        return null;
    }
}
