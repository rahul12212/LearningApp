package com.example.LearningApp.DTO;

import com.example.LearningApp.entity.Course;
import lombok.Data;

@Data
public class FavoriteCourseDTO {
    private Long id;
    private UserDTO user;
    private CourseDTO course;

    public Course getCourseId() {
        return null;
    }
}

