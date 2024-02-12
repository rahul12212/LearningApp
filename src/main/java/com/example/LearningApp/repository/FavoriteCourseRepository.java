package com.example.LearningApp.repository;

import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.FavoriteCourse;
import com.example.LearningApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {

    boolean existsByCourseAndUser(Course course, User user);
}
