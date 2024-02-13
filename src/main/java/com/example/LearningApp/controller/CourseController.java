package com.example.LearningApp.controller;

import com.example.LearningApp.DTO.CourseDTO;
import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDTO courseDTO, @RequestAttribute("user") User user) {
        // Convert CourseDTO to Course entity
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        Course createdCourse = courseService.createCourse(user, course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {
        // Convert CourseDTO to Course entity
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        courseService.updateCourse(id, course);
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }
}
