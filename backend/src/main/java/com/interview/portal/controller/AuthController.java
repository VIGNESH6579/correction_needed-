package com.interview.portal.controller;

import com.interview.portal.dto.LoginRequest;
import com.interview.portal.entity.User;
import com.interview.portal.service.UserService;
import com.interview.portal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration successful. Waiting for admin approval.");
            response.put("userId", savedUser.getId());
            response.put("email", savedUser.getEmail());
            response.put("status", savedUser.getStatus());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("user", new HashMap<String, Object>() {{
                put("id", user.getId());
                put("email", user.getEmail());
                put("name", user.getName());
                put("role", user.getRole());
                put("department", user.getDepartment());
                put("year", user.getYear());
            }});
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getEmailFromToken(token);
                User user = userService.getUserByEmail(email);

                Map<String, Object> response = new HashMap<>();
                response.put("valid", true);
                response.put("user", new HashMap<String, Object>() {{
                    put("id", user.getId());
                    put("email", user.getEmail());
                    put("name", user.getName());
                    put("role", user.getRole());
                }});
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new HashMap<String, String>() {{
                            put("message", "Invalid token");
                        }}
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new HashMap<String, String>() {{
                        put("message", "Token validation failed");
                    }}
            );
        }
    }
}
