package com.example.learningapp.service;

import com.example.learningapp.UnauthorizedException;
import com.example.learningapp.entity.FavoriteCourse;
import com.example.learningapp.entity.User;
import com.example.learningapp.repository.FavoriteCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<FavoriteCourse> getAllFavoriteCourses(User user) {
        return null;
    }

    public void removeFavoriteCourse(User user, Long id) {

    }
}
