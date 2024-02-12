package com.example.LearningApp.DTO;


import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private UserDTO user;
    private CourseDTO course;
}
