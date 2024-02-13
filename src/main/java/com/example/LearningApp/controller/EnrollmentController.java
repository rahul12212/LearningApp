package com.example.LearningApp.controller;



import com.example.LearningApp.entity.User;
import com.example.LearningApp.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollUser(@RequestParam Long userId) {
        boolean enrolled = enrollmentService.enrollUser(userId);
        if (enrolled) {
            return ResponseEntity.ok("User enrolled successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to enroll user.");
        }
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdrawEnrollment(@RequestParam Long userId) {
        boolean withdrawn = enrollmentService.withdrawEnrollment(userId);
        if (withdrawn) {
            return ResponseEntity.ok("User enrollment withdrawn successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to withdraw user enrollment.");
        }
    }
}
