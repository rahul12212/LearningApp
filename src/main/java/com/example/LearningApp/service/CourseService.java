package com.example.LearningApp.service;

import com.example.LearningApp.UnauthorizedException;
import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public Course createCourse(User user, Course course) {
        if (isAuthor(user)) {
            course.setAuthor(user);
            return courseRepository.save(course);
        } else {
            throw new UnauthorizedException("Only authors can create courses.");
        }
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
            // Update course properties with the new values from updatedCourse
            course.setTitle(updatedCourse.getTitle());
            course.setCategory(updatedCourse.getCategory());
            // Save the updated course
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

    // Check if the user is an author
    private boolean isAuthor(User user) {
        return user != null && user.getRole().equals("AUTHOR");
    }

    public Course findById(Course CourseId) {
        return null;
    }
}
