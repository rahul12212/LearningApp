package com.example.learningapp.controller;


import com.example.learningapp.service.EnrollmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollUser(@RequestParam Long userId) {
        boolean enrolled = enrollmentService.enrollUser(userId);
        if (enrolled) {
            log.info("User Enrolled");
            return ResponseEntity.ok("User enrolled successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to enroll user.");
        }
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdrawEnrollment(@RequestParam Long userId) {
        boolean withdrawn = enrollmentService.withdrawEnrollment(userId);
        if (withdrawn) {
            log.info("User is deleted");
            return ResponseEntity.ok("User enrollment withdrawn successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to withdraw user enrollment.");
        }
    }
}
