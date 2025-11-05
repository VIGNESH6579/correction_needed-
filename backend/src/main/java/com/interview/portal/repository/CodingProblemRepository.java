package com.interview.portal.repository;

import com.interview.portal.entity.CodingProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodingProblemRepository extends JpaRepository<CodingProblem, Long> {
    List<CodingProblem> findByDifficulty(CodingProblem.DifficultyLevel difficulty);
    List<CodingProblem> findByCategory(String category);
    List<CodingProblem> findByCategoryAndDifficulty(String category, CodingProblem.DifficultyLevel difficulty);
}
