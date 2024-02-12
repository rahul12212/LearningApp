package com.example.LearningApp.repository;

import com.example.LearningApp.entity.Course;
import com.example.LearningApp.entity.Enrollment;
import com.example.LearningApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByCourseAndUser(Course course, User user);
}
