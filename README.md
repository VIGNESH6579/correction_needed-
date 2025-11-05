# Interview Portal - Comprehensive Learning & Assessment Platform

## Overview

The Interview Portal is a full-stack web application designed to help students prepare for technical interviews through comprehensive courses, coding practice, and mock assessments. The platform provides a complete learning ecosystem with role-based access control, progress tracking, and detailed performance analytics.

## Key Features

### Course Learning
The platform offers comprehensive courses covering essential technical topics including Java Programming, Data Structures, Database Management, Operating Systems, Computer Networks, and Aptitude. Each course includes multiple-choice questions (MCQs) with detailed explanations to reinforce learning concepts.

### Coding Practice
Students can practice coding problems with varying difficulty levels (Easy, Medium, Hard). The integrated code editor allows students to write and test solutions with instant feedback. Problems are categorized by topic to help students focus on specific areas.

### Mock Tests
Timed mock tests simulate real interview scenarios with auto-submit functionality. Students receive detailed results including correct answers, wrong answers, percentage scores, and pass/fail status based on configurable passing scores.

### User Management
The platform features secure JWT-based authentication with role-based access control. Admin users can approve or reject student registrations, manage user accounts, and view comprehensive analytics. Students can track their progress and learning history.

### Admin Control Panel
Administrators have access to a comprehensive dashboard for managing users, content, and viewing platform statistics. The admin panel includes features for user approval, content management, and performance analytics.

### Responsive Design
The application is fully responsive and works seamlessly across desktop, tablet, and mobile devices. The modern UI provides an intuitive user experience with clear navigation and accessible design.

## Technology Stack

### Frontend
- **HTML5** - Semantic markup and structure
- **CSS3** - Responsive styling with modern layouts
- **JavaScript (ES6+)** - Interactive features and API integration
- **Fetch API** - Asynchronous HTTP requests

### Backend
- **Spring Boot 2.7.14** - Java web framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Object-relational mapping
- **JWT (JSON Web Tokens)** - Secure token-based authentication
- **Maven** - Dependency management and build automation

### Database
- **MySQL 8.0** - Relational database management system
- **Drizzle ORM** - Optional for schema management

### Architecture
The application follows a three-tier architecture with clear separation of concerns:
- **Presentation Layer** - Frontend HTML, CSS, and JavaScript
- **Business Logic Layer** - Spring Boot services and controllers
- **Data Access Layer** - JPA repositories and database operations

## Project Structure

```
interview-portal-complete/
├── backend/                          # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/interview/portal/
│   │   │   │   ├── config/          # Security and CORS configuration
│   │   │   │   ├── controller/      # REST API endpoints
│   │   │   │   ├── entity/          # JPA entities
│   │   │   │   ├── repository/      # Data access interfaces
│   │   │   │   ├── service/         # Business logic
│   │   │   │   ├── util/            # Utility classes (JWT)
│   │   │   │   └── dto/             # Data transfer objects
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                    # Unit tests
│   └── pom.xml                      # Maven configuration
│
├── frontend/                         # Frontend Application
│   ├── pages/                       # HTML pages
│   │   ├── login.html
│   │   ├── register.html
│   │   ├── dashboard.html
│   │   ├── courses.html
│   │   ├── coding.html
│   │   ├── tests.html
│   │   └── admin.html
│   ├── css/
│   │   └── styles.css              # Responsive stylesheet
│   ├── js/
│   │   └── app.js                  # Utilities and API integration
│   └── index.html                  # Landing page
│
├── database/                        # Database Scripts
│   ├── schema.sql                  # Database schema
│   └── sample_data.sql             # Sample test data
│
└── docs/                           # Documentation
    ├── README.md                   # Project overview
    ├── SETUP.md                    # Setup instructions
    └── PROJECT_STRUCTURE.md        # File organization
```

## Default Credentials

The application comes with pre-configured test accounts for demonstration purposes:

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@portal.com | Admin@123 |
| Student | student@portal.com | Student@123 |

**Important:** Change these credentials in production environments for security purposes.

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login
- `GET /api/auth/validate-token` - Validate JWT token

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course details
- `GET /api/courses/{courseId}/questions` - Get course questions
- `POST /api/courses` - Create course (Admin only)
- `PUT /api/courses/{id}` - Update course (Admin only)
- `DELETE /api/courses/{id}` - Delete course (Admin only)

### Coding Problems
- `GET /api/coding/problems` - Get all problems
- `GET /api/coding/problems/{id}` - Get problem details
- `GET /api/coding/problems/difficulty/{difficulty}` - Filter by difficulty
- `POST /api/coding/problems` - Create problem (Admin only)
- `PUT /api/coding/problems/{id}` - Update problem (Admin only)
- `DELETE /api/coding/problems/{id}` - Delete problem (Admin only)

### Tests
- `GET /api/tests` - Get all tests
- `GET /api/tests/active` - Get active tests
- `GET /api/tests/{id}` - Get test details
- `POST /api/tests/{testId}/submit` - Submit test results
- `GET /api/tests/user/results` - Get user test results
- `POST /api/tests` - Create test (Admin only)
- `PUT /api/tests/{id}` - Update test (Admin only)
- `DELETE /api/tests/{id}` - Delete test (Admin only)

### Admin
- `GET /api/admin/users` - Get all users
- `GET /api/admin/students` - Get all students
- `GET /api/admin/pending-approvals` - Get pending approvals
- `PUT /api/admin/users/{userId}/approve` - Approve user
- `PUT /api/admin/users/{userId}/reject` - Reject user
- `DELETE /api/admin/users/{userId}` - Delete user
- `GET /api/admin/stats` - Get admin statistics

## Security Features

### Authentication
The application uses JWT (JSON Web Tokens) for secure, stateless authentication. Tokens are generated upon successful login and must be included in the Authorization header for protected endpoints.

### Authorization
Role-based access control (RBAC) ensures that only authorized users can access specific resources. Admin endpoints are protected and require ADMIN role. Student-specific endpoints require authentication but not admin privileges.

### Password Security
User passwords are encrypted using BCrypt hashing algorithm with configurable salt rounds. Passwords are never stored in plain text.

### CORS Configuration
Cross-Origin Resource Sharing (CORS) is configured to allow requests from specified origins. This prevents unauthorized cross-origin requests while enabling legitimate frontend-backend communication.

## Database Schema

### Users Table
Stores user account information including email, password, role, and approval status.

### Courses Table
Contains course information including name, description, and total number of questions.

### Questions Table
Stores multiple-choice questions associated with courses, including options and correct answers.

### Coding Problems Table
Contains coding practice problems with difficulty levels, categories, and sample test cases.

### Tests Table
Stores mock test configurations including duration, total questions, and passing scores.

### Test Results Table
Records student test submissions including scores, answers, and completion times.

## Performance Considerations

### Database Optimization
- Proper indexing on frequently queried columns
- Foreign key relationships for data integrity
- Normalized schema design to reduce redundancy

### API Performance
- Pagination support for large datasets
- Efficient query filtering and sorting
- Caching mechanisms for frequently accessed data

### Frontend Performance
- Lazy loading of resources
- Minified CSS and JavaScript
- Optimized image assets

## Browser Compatibility

The application is compatible with all modern browsers including:
- Chrome/Chromium (latest versions)
- Firefox (latest versions)
- Safari (latest versions)
- Edge (latest versions)

## Future Enhancements

Potential features for future development include:
- Real-time code execution and testing
- Video tutorials and interactive lessons
- Discussion forums and peer learning
- Detailed performance analytics and insights
- Mobile native applications
- Integration with coding judges
- Gamification and achievement badges

## Support and Contribution

For issues, bug reports, or feature requests, please contact the development team or submit issues through the project repository.

## License

This project is provided as-is for educational and learning purposes.

## Disclaimer

This application is designed for educational purposes. The default credentials should be changed before deploying to production. Ensure proper security measures are implemented for production deployments.
