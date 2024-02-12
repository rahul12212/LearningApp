package com.example.LearningApp.service;

import com.example.LearningApp.UnauthorizedException;
import com.example.LearningApp.entity.FavoriteCourse;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.repository.FavoriteCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteCourseService {
    @Autowired
    private FavoriteCourseRepository favoriteCourseRepository;

    @Autowired
    private UserService userService;

    public FavoriteCourse addFavoriteCourse(User user, FavoriteCourse favoriteCourse) {

        if (userService.isLearner(user)) {

            return favoriteCourseRepository.save(favoriteCourse);
        } else {
            throw new UnauthorizedException("Only learners can add favorite courses.");
        }
    }


}
