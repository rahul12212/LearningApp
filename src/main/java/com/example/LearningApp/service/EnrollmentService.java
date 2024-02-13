package com.example.LearningApp.service;

import com.example.LearningApp.UnauthorizedException;
import com.example.LearningApp.entity.Enrollment;
import com.example.LearningApp.entity.User;
import com.example.LearningApp.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserService userService;

    public Enrollment enrollStudent(User user, Enrollment enrollment) {

        if (userService.isAdmin(user) || userService.isLearner(user)) {

            return enrollmentRepository.save(enrollment);
        } else {
            throw new UnauthorizedException("Only admins and learners can enroll students.");
        }
    }


    public abstract Enrollment enrollStudent(Enrollment enrollment);

    public boolean enrollUser(Long userId) {
        return false;
    }

    public boolean withdrawEnrollment(Long userId) {
        return false;
    }
}
