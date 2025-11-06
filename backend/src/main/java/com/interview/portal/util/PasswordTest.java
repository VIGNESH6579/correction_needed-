package com.interview.portal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Test the hash from sample_data.sql
        String hashFromDB = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm";
        
        // Test various passwords
        String[] testPasswords = {"password123", "password", "admin123", "admin", "123456"};
        
        System.out.println("Testing hash: " + hashFromDB);
        System.out.println("\nTesting passwords:");
        for (String pwd : testPasswords) {
            boolean matches = encoder.matches(pwd, hashFromDB);
            System.out.println("Password '" + pwd + "': " + (matches ? "✓ MATCHES" : "✗ No match"));
        }
        
        // Generate new hash for "password123"
        System.out.println("\n--- Generating new hash for 'password123' ---");
        String newHash = encoder.encode("password123");
        System.out.println("New hash: " + newHash);
        System.out.println("Verification: " + encoder.matches("password123", newHash));
        
        // Generate hash for "admin"
        System.out.println("\n--- Generating new hash for 'admin' ---");
        String adminHash = encoder.encode("admin");
        System.out.println("New hash: " + adminHash);
        System.out.println("Verification: " + encoder.matches("admin", adminHash));
    }
}
