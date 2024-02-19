package com.example.LearningApp.repository;

import com.example.LearningApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE role = ?1", nativeQuery = true)
    List<User> getUserByRole(String role);

    List<User> findByRoleIgnoreCase(String role);
}
