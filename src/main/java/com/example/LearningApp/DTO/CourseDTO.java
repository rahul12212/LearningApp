package com.example.LearningApp.DTO;


import lombok.Builder;
import lombok.Data;

@Data


public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
}
