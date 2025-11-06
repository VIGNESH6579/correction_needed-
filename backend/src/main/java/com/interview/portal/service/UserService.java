package com.interview.portal.service;

import com.interview.portal.entity.User;
import com.interview.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.UserRole.STUDENT);
        user.setStatus(User.UserStatus.PENDING);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        logger.info("=== LOGIN ATTEMPT ===");
        logger.info("Email: {}", email);
        logger.info("Password length: {}", password.length());
        
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            logger.error("User not found: {}", email);
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        logger.info("User found - ID: {}, Email: {}, Role: {}, Status: {}", 
                    user.getId(), user.getEmail(), user.getRole(), user.getStatus());
        logger.info("Password hash from DB: {}", user.getPassword());

        if (!user.getStatus().equals(User.UserStatus.APPROVED)) {
            logger.error("User not approved: {}", email);
            throw new RuntimeException("User account is not approved yet");
        }

        logger.info("Attempting password match...");
        boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
        logger.info("Password matches: {}", passwordMatches);
        
        if (!passwordMatches) {
            logger.error("Password mismatch for user: {}", email);
            throw new RuntimeException("Invalid password");
        }

        logger.info("Login successful for: {}", email);
        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllStudents() {
        return userRepository.findByRole(User.UserRole.STUDENT);
    }

    public List<User> getPendingApprovals() {
        return userRepository.findByStatus(User.UserStatus.PENDING);
    }

    public User approveUser(Long userId) {
        User user = getUserById(userId);
        user.setStatus(User.UserStatus.APPROVED);
        return userRepository.save(user);
    }

    public User rejectUser(Long userId) {
        User user = getUserById(userId);
        user.setStatus(User.UserStatus.REJECTED);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
