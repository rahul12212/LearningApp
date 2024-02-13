package com.example.LearningApp.controller;

import com.example.LearningApp.DTO.FavoriteCourseDTO;
import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.FavoriteCourse;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.service.CourseService;
import com.example.LearningApp.service.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-courses")
public class FavoriteCourseController {

    @Autowired
    private FavoriteCourseService favoriteCourseService;

    @Autowired
    private CourseService courseService; // Import your CourseService

    @PostMapping("/add")
    public ResponseEntity<FavoriteCourse> addFavoriteCourse(@RequestBody FavoriteCourseDTO favoriteCourseDTO, @RequestAttribute("user") User user) {
        FavoriteCourse favoriteCourse = new FavoriteCourse();

        // Set the User object
        favoriteCourse.setUser(user);

        // Assuming favoriteCourseDTO.getCourseId() returns the ID of the course
        // Fetch the Course entity from the database based on the ID
        Course course = courseService.findById(favoriteCourseDTO.getCourseId()); // You need to have a method in your service to fetch the Course by ID

        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Set the Course object
        favoriteCourse.setCourse(course);

        // Add the FavoriteCourse
        FavoriteCourse addedFavoriteCourse = favoriteCourseService.addFavoriteCourse(user, favoriteCourse);

        return new ResponseEntity<>(addedFavoriteCourse, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FavoriteCourse>> getAllFavoriteCourses(@RequestAttribute("user") User user) {
        List<FavoriteCourse> favoriteCourses = favoriteCourseService.getAllFavoriteCourses(user);
        return new ResponseEntity<>(favoriteCourses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FavoriteCourse> removeFavoriteCourse(@PathVariable("id") Long id, @RequestAttribute("user") User user) {
        favoriteCourseService.removeFavoriteCourse(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
