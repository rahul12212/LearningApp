package com.example.LearningApp.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
