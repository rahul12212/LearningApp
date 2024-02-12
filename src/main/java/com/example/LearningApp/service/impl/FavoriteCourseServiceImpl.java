package com.example.LearningApp.service.impl;

import com.example.LearningApp.entity.FavoriteCourse;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.repository.FavoriteCourseRepository;
import com.example.LearningApp.service.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteCourseServiceImpl extends FavoriteCourseService {
    @Autowired
    private FavoriteCourseRepository favoriteCourseRepository;

    @Override
    public FavoriteCourse addFavoriteCourse(User user, FavoriteCourse favoriteCourse) {
        if (favoriteCourseRepository.existsByCourseAndUser(favoriteCourse.getCourse(), user)) {
            throw new IllegalArgumentException("Course is already added to favorites.");
        }
        favoriteCourse.setUser(user);
        return favoriteCourseRepository.save(favoriteCourse);
    }
}
