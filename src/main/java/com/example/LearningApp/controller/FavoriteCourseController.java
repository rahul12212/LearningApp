package com.example.learningapp.controller;

import com.example.learningapp.dto.FavoriteCourseDTO;
import com.example.learningapp.entity.Course;
import com.example.learningapp.entity.FavoriteCourse;
import com.example.learningapp.entity.User;
import com.example.learningapp.service.CourseService;
import com.example.learningapp.service.FavoriteCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/favorite-courses")
public class FavoriteCourseController {

    @Autowired
    private FavoriteCourseService favoriteCourseService;

    @Autowired
    private CourseService courseService; // Import your CourseService

    @PostMapping("/add")
    public ResponseEntity<FavoriteCourse> addFavoriteCourse(@RequestBody FavoriteCourseDTO favoriteCourseDTO, @RequestAttribute("user") User user) {
        FavoriteCourse favoriteCourse = new FavoriteCourse();

        favoriteCourse.setUser(user);


        Course course = courseService.findById(favoriteCourseDTO.getCourseId());

        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Set the Course object
        favoriteCourse.setCourse(course);

        // Add the FavoriteCourse
        FavoriteCourse addedFavoriteCourse = favoriteCourseService.addFavoriteCourse(user, favoriteCourse);

        log.info("Fav Course Added");

        return new ResponseEntity<>(addedFavoriteCourse, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FavoriteCourse>> getAllFavoriteCourses(@RequestAttribute("user") User user) {
        List<FavoriteCourse> favoriteCourses = favoriteCourseService.getAllFavoriteCourses(user);
        log.info("Course listed");
        return new ResponseEntity<>(favoriteCourses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FavoriteCourse> removeFavoriteCourse(@PathVariable("id") Long id, @RequestAttribute("user") User user) {
        favoriteCourseService.removeFavoriteCourse(user, id);
        log.info("Fav Course Deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
