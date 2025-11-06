-- Simple Admin Password Reset
-- This will set the admin password to "admin123" with a freshly generated hash

USE interview_portal;

-- Set admin password to "admin123" 
-- Hash generated using: BCryptPasswordEncoder().encode("admin123")
UPDATE users 
SET password = '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',
    status = 'APPROVED',
    created_at = CURRENT_TIMESTAMP,
    updated_at = CURRENT_TIMESTAMP
WHERE email = 'admin@portal.com';

SELECT 'Password reset complete!' as status, 
       'Email: admin@portal.com' as email, 
       'Password: admin123' as password;
