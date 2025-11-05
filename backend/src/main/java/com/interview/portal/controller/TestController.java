package com.interview.portal.controller;

import com.interview.portal.entity.Test;
import com.interview.portal.entity.TestResult;
import com.interview.portal.entity.User;
import com.interview.portal.service.TestService;
import com.interview.portal.service.UserService;
import com.interview.portal.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testService.getAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Test>> getActiveTests() {
        List<Test> tests = testService.getActiveTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Long id) {
        try {
            Test test = testService.getTestById(id);
            return ResponseEntity.ok(test);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Test> createTest(@RequestBody Test test) {
        Test createdTest = testService.createTest(test);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody Test testDetails) {
        try {
            Test updatedTest = testService.updateTest(id, testDetails);
            return ResponseEntity.ok(updatedTest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTest(@PathVariable Long id) {
        try {
            testService.deleteTest(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Test deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{testId}/submit")
    public ResponseEntity<?> submitTest(@PathVariable Long testId,
                                       @RequestHeader("Authorization") String authHeader,
                                       @RequestBody Map<String, Object> submissionData) {
        try {
            String token = authHeader.substring(7);
            String email = jwtUtil.getEmailFromToken(token);
            User user = userService.getUserByEmail(email);
            Test test = testService.getTestById(testId);

            Integer score = ((Number) submissionData.get("score")).intValue();
            Integer correctAnswers = ((Number) submissionData.get("correctAnswers")).intValue();
            Integer wrongAnswers = ((Number) submissionData.get("wrongAnswers")).intValue();
            Integer skippedQuestions = ((Number) submissionData.get("skippedQuestions")).intValue();
            Integer timeTakenSeconds = ((Number) submissionData.get("timeTakenSeconds")).intValue();

            TestResult result = testService.submitTestResult(user, test, score, correctAnswers, 
                    wrongAnswers, skippedQuestions, timeTakenSeconds);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Test submitted successfully");
            response.put("result", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error submitting test: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/user/results")
    public ResponseEntity<?> getUserTestResults(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            String email = jwtUtil.getEmailFromToken(token);
            User user = userService.getUserByEmail(email);
            List<TestResult> results = testService.getUserTestResults(user.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getId());
            response.put("results", results);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error fetching test results");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
