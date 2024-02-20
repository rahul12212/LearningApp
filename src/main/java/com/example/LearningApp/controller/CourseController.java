package com.example.learningapp.controller;

import com.example.learningapp.dto.CourseDTO;
import com.example.learningapp.entity.Course;
import com.example.learningapp.entity.User;
import com.example.learningapp.service.CourseService;
import com.example.learningapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) {


        User user = UserService.getCurrentUser();


        if (courseDTO == null) {
            return ResponseEntity.badRequest().body("Course details are required");
        }


        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        System.out.println(course);

        try {
            Course createdCourse = courseService.createCourse(user, course);
            log.info("Course created");
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create course: " + e.getMessage());
        }
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
            log.info("Course list displayed");
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable("id") Long id, @RequestBody CourseDTO courseDTO) {

        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);

        courseService.updateCourse(id, course);
        log.info("Course updated");
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        log.info("Course deleted");
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }
}

//Code formatted