package com.example.LearningApp.service.impl;

import com.example.LearningApp.entity.Enrollment;
import com.example.LearningApp.repository.EnrollmentRepository;
import com.example.LearningApp.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl extends EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment enrollStudent(Enrollment enrollment) {
        if (enrollmentRepository.existsByCourseAndUser(enrollment.getCourse(), enrollment.getUser())) {
            throw new IllegalArgumentException("Student is already enrolled in this course.");
        }
        return enrollmentRepository.save(enrollment);
    }
}
