package com.example.LearningApp.service;

import com.example.LearningApp.entity.User;
import com.example.LearningApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(User user) {

        if(user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isAdmin(User user) {
        return user.getRole().equals("ADMIN");
    }

    public static boolean isAuthor(User user) {
        return user.getRole().equals("AUTHOR");
    }

    public boolean isLearner(User user) {
        return user.getRole().equals("LEARNER");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id, User user) {

    }

    public void deleteUser(Long id) {

    }
}
