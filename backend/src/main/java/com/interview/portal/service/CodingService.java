package com.interview.portal.service;

import com.interview.portal.entity.CodingProblem;
import com.interview.portal.repository.CodingProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodingService {

    @Autowired
    private CodingProblemRepository codingProblemRepository;

    public List<CodingProblem> getAllProblems() {
        return codingProblemRepository.findAll();
    }

    public CodingProblem getProblemById(Long id) {
        return codingProblemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coding problem not found"));
    }

    public List<CodingProblem> getProblemsByDifficulty(CodingProblem.DifficultyLevel difficulty) {
        return codingProblemRepository.findByDifficulty(difficulty);
    }

    public List<CodingProblem> getProblemsByCategory(String category) {
        return codingProblemRepository.findByCategory(category);
    }

    public List<CodingProblem> getProblemsByCategoryAndDifficulty(String category, CodingProblem.DifficultyLevel difficulty) {
        return codingProblemRepository.findByCategoryAndDifficulty(category, difficulty);
    }

    public CodingProblem createProblem(CodingProblem problem) {
        return codingProblemRepository.save(problem);
    }

    public CodingProblem updateProblem(Long id, CodingProblem problemDetails) {
        CodingProblem problem = getProblemById(id);
        problem.setTitle(problemDetails.getTitle());
        problem.setDescription(problemDetails.getDescription());
        problem.setDifficulty(problemDetails.getDifficulty());
        problem.setCategory(problemDetails.getCategory());
        problem.setSampleInput(problemDetails.getSampleInput());
        problem.setSampleOutput(problemDetails.getSampleOutput());
        problem.setConstraints(problemDetails.getConstraints());
        return codingProblemRepository.save(problem);
    }

    public void deleteProblem(Long id) {
        codingProblemRepository.deleteById(id);
    }
}
