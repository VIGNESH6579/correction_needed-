package com.interview.portal.repository;

import com.interview.portal.entity.Question;
import com.interview.portal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCourse(Course course);
    List<Question> findByCourseId(Long courseId);
    long countByCourseId(Long courseId);
}
