package com.interview.portal.repository;

import com.interview.portal.entity.TestResult;
import com.interview.portal.entity.User;
import com.interview.portal.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findByUser(User user);
    List<TestResult> findByTest(Test test);
    List<TestResult> findByUserAndTest(User user, Test test);
    List<TestResult> findByUserIdOrderByCompletedAtDesc(Long userId);
    Optional<TestResult> findFirstByUserAndTestOrderByCompletedAtDesc(User user, Test test);
}
