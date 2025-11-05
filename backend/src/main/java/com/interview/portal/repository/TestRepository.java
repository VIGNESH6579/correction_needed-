package com.interview.portal.repository;

import com.interview.portal.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Optional<Test> findByTitle(String title);
    List<Test> findByIsActive(Boolean isActive);
    List<Test> findAll();
}
