package com.example.learningapp.service.impl;

import com.example.learningapp.entity.FavoriteCourse;
import com.example.learningapp.entity.User;
import com.example.learningapp.repository.FavoriteCourseRepository;
import com.example.learningapp.service.FavoriteCourseService;
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
