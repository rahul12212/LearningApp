package com.example.LearningApp.service.impl;

import com.example.LearningApp.UnauthorizedException;
import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.repository.CourseRepository;
import com.example.LearningApp.service.CourseService;
import com.example.LearningApp.service.UserService;
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
        if (!userService.isAuthor(user)) {
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
