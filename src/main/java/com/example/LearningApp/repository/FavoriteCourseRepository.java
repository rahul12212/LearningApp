package com.example.learningapp.repository;

import com.example.learningapp.entity.Course;
import com.example.learningapp.entity.FavoriteCourse;
import com.example.learningapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {

    boolean existsByCourseAndUser(Course course, User user);
}
