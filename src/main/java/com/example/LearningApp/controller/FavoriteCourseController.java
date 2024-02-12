package com.example.LearningApp.controller;

import com.example.LearningApp.entity.FavoriteCourse;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.service.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite-courses")
public class FavoriteCourseController {

    @Autowired
    private FavoriteCourseService favoriteCourseService;

    @PostMapping("/add")
    public ResponseEntity<FavoriteCourse> addFavoriteCourse(@RequestBody FavoriteCourse favoriteCourse, @RequestAttribute("user") User user) {
        FavoriteCourse addedFavoriteCourse = favoriteCourseService.addFavoriteCourse(user, favoriteCourse);
        return new ResponseEntity<>(addedFavoriteCourse, HttpStatus.CREATED);
    }

    // Other favorite course-related endpoints can be added here
}
