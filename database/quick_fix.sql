-- Quick Fix for Admin Password
USE interview_portal;

-- Update admin password to correct BCrypt hash for "password123"
UPDATE users 
SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/KFm',
    status = 'APPROVED',
    created_at = CURRENT_TIMESTAMP,
    updated_at = CURRENT_TIMESTAMP
WHERE email = 'admin@portal.com';

-- Verify the update
SELECT email, LEFT(password, 20) as password_hash, status, role, created_at 
FROM users 
WHERE email = 'admin@portal.com';
