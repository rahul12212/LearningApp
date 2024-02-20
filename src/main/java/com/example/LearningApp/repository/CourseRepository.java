package com.example.learningapp.repository;

import com.example.learningapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategory(String category);
}
