package com.example.learningapp.service.impl;

import com.example.learningapp.UnauthorizedException;
import com.example.learningapp.entity.Course;
import com.example.learningapp.entity.User;
import com.example.learningapp.repository.CourseRepository;
import com.example.learningapp.service.CourseService;
import com.example.learningapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl extends CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    @Override
    public Course createCourse(User user, Course course) {
        UserService userService = null;
        if (!UserService.isAuthor(user)) {
            throw new UnauthorizedException("Only authors can create courses.");
        }
        course.setAuthor(user);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
