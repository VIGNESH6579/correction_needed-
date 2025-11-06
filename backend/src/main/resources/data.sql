-- Interview Portal Sample Data (classpath)
-- Loaded by Spring Boot after schema.sql

-- Insert Admin User
INSERT IGNORE INTO users (email, password, name, department, year, role, status) VALUES
('admin@portal.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm', 'Admin User', 'ADMIN', 0, 'ADMIN', 'APPROVED');

-- Insert Student Users
INSERT IGNORE INTO users (email, password, name, department, year, role, status) VALUES
('student1@portal.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm', 'John Doe', 'CSE', 3, 'STUDENT', 'APPROVED'),
('student2@portal.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm', 'Jane Smith', 'ECE', 2, 'STUDENT', 'APPROVED'),
('student3@portal.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm', 'Mike Johnson', 'CSE', 4, 'STUDENT', 'APPROVED'),
('student4@portal.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm', 'Sarah Williams', 'ME', 1, 'STUDENT', 'PENDING');

-- Insert Courses
INSERT IGNORE INTO courses (name, description, total_questions) VALUES
('Java Programming', 'Learn core Java concepts, OOP principles, and advanced features', 50),
('Data Structures', 'Master Arrays, Linked Lists, Trees, Graphs, and Sorting Algorithms', 45),
('Database Management', 'SQL, Normalization, Transactions, and Database Design', 35),
('Operating Systems', 'Processes, Memory Management, File Systems, and Scheduling', 40),
('Computer Networks', 'TCP/IP, DNS, HTTP, Security, and Network Protocols', 30),
('Aptitude', 'Quantitative Reasoning, Logical Reasoning, and Problem Solving', 60);

-- Insert Questions for Java Programming Course
INSERT IGNORE INTO questions (course_id, question_text, option_a, option_b, option_c, option_d, correct_answer, explanation) VALUES
(1, 'What is the output of System.out.println(10 + 20 + "30")?', '30', '1020', '3030', '102030', 'B', 'String concatenation happens from left to right. 10 + 20 = 30, then 30 + "30" = "3030"'),
(1, 'Which keyword is used to create a class in Java?', 'class', 'Class', 'CLASS', 'struct', 'A', 'The keyword "class" is used to declare a class in Java'),
(1, 'What is the default value of a boolean variable in Java?', 'true', 'false', 'null', '0', 'B', 'The default value of a boolean variable is false'),
(1, 'Which of the following is NOT a primitive data type in Java?', 'int', 'float', 'String', 'double', 'C', 'String is a reference type, not a primitive data type'),
(1, 'What does JVM stand for?', 'Java Virtual Machine', 'Java Variable Method', 'Java Verification Module', 'Java Version Manager', 'A', 'JVM stands for Java Virtual Machine');

-- Insert Questions for Data Structures Course
INSERT IGNORE INTO questions (course_id, question_text, option_a, option_b, option_c, option_d, correct_answer, explanation) VALUES
(2, 'What is the time complexity of binary search?', 'O(n)', 'O(log n)', 'O(n log n)', 'O(n^2)', 'B', 'Binary search has a time complexity of O(log n)'),
(2, 'Which data structure uses LIFO principle?', 'Queue', 'Stack', 'Tree', 'Graph', 'B', 'Stack uses Last In First Out (LIFO) principle'),
(2, 'What is the space complexity of a linked list?', 'O(1)', 'O(log n)', 'O(n)', 'O(n^2)', 'C', 'Space complexity of a linked list is O(n) for storing n elements');

-- Insert Questions for Database Management Course
INSERT IGNORE INTO questions (course_id, question_text, option_a, option_b, option_c, option_d, correct_answer, explanation) VALUES
(3, 'What does ACID stand for?', 'Atomicity, Consistency, Isolation, Durability', 'Access, Control, Index, Data', 'Attribute, Column, Integer, Double', 'Array, Class, Interface, Database', 'A', 'ACID stands for Atomicity, Consistency, Isolation, and Durability');

-- Insert Coding Problems
INSERT IGNORE INTO coding_problems (title, description, difficulty, category, sample_input, sample_output, constraints) VALUES
('Two Sum', 'Given an array of integers nums and an integer target, return the indices of the two numbers that add up to target.', 'EASY', 'arrays', 'nums = [2,7,11,15], target = 9', '[0,1]', '2 <= nums.length <= 10^4'),
('Reverse Linked List', 'Reverse a singly linked list.', 'MEDIUM', 'linked lists', '1->2->3->NULL', '3->2->1->NULL', '0 <= Node.val <= 100'),
('Binary Tree Inorder Traversal', 'Given the root of a binary tree, return the inorder traversal of its nodes\' values.', 'EASY', 'trees', '[1,null,2,3]', '[1,3,2]', '0 <= number of nodes <= 100'),
('Merge Intervals', 'Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals.', 'MEDIUM', 'arrays', '[[1,3],[2,6],[8,10],[15,18]]', '[[1,6],[8,10],[15,18]]', '1 <= intervals.length <= 10^4'),
('Trapping Rain Water', 'Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.', 'HARD', 'arrays', '[0,1,0,2,1,0,1,3,2,1,2,1]', '6', '1 <= height.length <= 2 * 10^4');

-- Insert Tests
INSERT IGNORE INTO tests (title, description, duration_minutes, total_questions, passing_score, is_active) VALUES
('Java Fundamentals Test', 'Test your knowledge of Java basics and OOP concepts', 30, 20, 60, TRUE),
('Data Structures Assessment', 'Assess your understanding of data structures and algorithms', 45, 25, 65, TRUE),
('DBMS Concept Test', 'Test your knowledge of database management systems', 35, 22, 60, TRUE),
('Aptitude Practice', 'Practice quantitative and logical reasoning', 60, 40, 70, TRUE),
('Full Stack Assessment', 'Comprehensive test covering all topics', 120, 100, 70, TRUE);

-- Insert Sample Test Results
INSERT IGNORE INTO test_results (user_id, test_id, score, total_questions, correct_answers, wrong_answers, skipped_questions, percentage, status, time_taken_seconds, started_at, completed_at) VALUES
(2, 1, 18, 20, 18, 2, 0, 90.00, 'PASSED', 1500, '2024-01-15 10:00:00', '2024-01-15 10:25:00'),
(2, 2, 16, 25, 16, 9, 0, 64.00, 'PASSED', 2700, '2024-01-16 14:00:00', '2024-01-16 14:45:00'),
(3, 1, 12, 20, 12, 8, 0, 60.00, 'PASSED', 1800, '2024-01-17 11:00:00', '2024-01-17 11:30:00'),
(3, 3, 13, 22, 13, 9, 0, 59.09, 'FAILED', 2100, '2024-01-18 15:00:00', '2024-01-18 15:35:00');