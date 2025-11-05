package com.interview.portal.service;

import com.interview.portal.entity.User;
import com.interview.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        if (!user.getStatus().equals(User.UserStatus.APPROVED)) {
            throw new RuntimeException("User account is not approved yet");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

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
