package com.interview.portal.service;

import com.interview.portal.entity.Course;
import com.interview.portal.entity.Question;
import com.interview.portal.repository.CourseRepository;
import com.interview.portal.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setTotalQuestions(courseDetails.getTotalQuestions());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Question> getCourseQuestions(Long courseId) {
        return questionRepository.findByCourseId(courseId);
    }

    public Question addQuestionToCourse(Long courseId, Question question) {
        Course course = getCourseById(courseId);
        question.setCourse(course);
        return questionRepository.save(question);
    }

    public long getQuestionCount(Long courseId) {
        return questionRepository.countByCourseId(courseId);
    }
}
