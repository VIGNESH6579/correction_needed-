-- Fix Script for Zero Date and Password Issues
-- Run this script to fix the database issues

USE interview_portal;

-- Fix all zero date values in users table
UPDATE users 
SET created_at = CURRENT_TIMESTAMP 
WHERE created_at = '0000-00-00 00:00:00' OR created_at IS NULL;

UPDATE users 
SET updated_at = CURRENT_TIMESTAMP 
WHERE updated_at = '0000-00-00 00:00:00' OR updated_at IS NULL;

-- Reset admin password to "password123" (bcrypt encoded)
-- This is the correct bcrypt hash that matches "password123"
UPDATE users 
SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm' 
WHERE email = 'admin@portal.com';

-- Also reset student passwords to "password123" for testing
UPDATE users 
SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm' 
WHERE role = 'STUDENT';

-- Fix zero dates in other tables
UPDATE courses 
SET created_at = CURRENT_TIMESTAMP 
WHERE created_at = '0000-00-00 00:00:00' OR created_at IS NULL;

UPDATE courses 
SET updated_at = CURRENT_TIMESTAMP 
WHERE updated_at = '0000-00-00 00:00:00' OR updated_at IS NULL;

UPDATE questions 
SET created_at = CURRENT_TIMESTAMP 
WHERE created_at = '0000-00-00 00:00:00' OR created_at IS NULL;

UPDATE questions 
SET updated_at = CURRENT_TIMESTAMP 
WHERE updated_at = '0000-00-00 00:00:00' OR updated_at IS NULL;

UPDATE coding_problems 
SET created_at = CURRENT_TIMESTAMP 
WHERE created_at = '0000-00-00 00:00:00' OR created_at IS NULL;

UPDATE coding_problems 
SET updated_at = CURRENT_TIMESTAMP 
WHERE updated_at = '0000-00-00 00:00:00' OR updated_at IS NULL;

UPDATE tests 
SET created_at = CURRENT_TIMESTAMP 
WHERE created_at = '0000-00-00 00:00:00' OR created_at IS NULL;

UPDATE tests 
SET updated_at = CURRENT_TIMESTAMP 
WHERE updated_at = '0000-00-00 00:00:00' OR updated_at IS NULL;

UPDATE test_results 
SET created_at = CURRENT_TIMESTAMP 
WHERE created_at = '0000-00-00 00:00:00' OR created_at IS NULL;

-- Verify the changes
SELECT 'Admin user:' as info, email, password, created_at, updated_at FROM users WHERE email = 'admin@portal.com';
SELECT 'Student users count:' as info, COUNT(*) as count FROM users WHERE role = 'STUDENT';
SELECT 'Users with valid dates:' as info, COUNT(*) as count FROM users WHERE created_at != '0000-00-00 00:00:00';

-- Show password for reference
SELECT '
Login Credentials After Fix:
---------------------------
Admin:
  Email: admin@portal.com
  Password: password123

Students:
  Email: student1@portal.com, student2@portal.com, etc.
  Password: password123
' as credentials;
