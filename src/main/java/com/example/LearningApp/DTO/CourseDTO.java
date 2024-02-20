package com.example.learningapp.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String category;
    private String author;
    private String password;
}
