package com.example.LearningApp.controller;

import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course, @RequestAttribute("user") User user) {
        Course createdCourse = courseService.createCourse(user, course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }


}
