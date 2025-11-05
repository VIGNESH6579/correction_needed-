# Interview Portal - Project Structure Documentation

This document provides a comprehensive overview of the project directory structure and explains the purpose of each file and folder.

## Root Directory Structure

```
interview-portal-complete/
├── backend/                    # Spring Boot Backend Application
├── frontend/                   # Frontend Web Application
├── database/                   # Database Scripts
├── docs/                       # Documentation Files
├── README.md                   # Project Overview
├── SETUP.md                    # Setup Instructions
└── PROJECT_STRUCTURE.md        # This File
```

## Backend Directory Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/interview/portal/
│   │   │       ├── config/                 # Configuration Classes
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   └── CorsConfig.java
│   │   │       ├── controller/             # REST API Controllers
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── CourseController.java
│   │   │       │   ├── CodingController.java
│   │   │       │   ├── TestController.java
│   │   │       │   └── AdminController.java
│   │   │       ├── entity/                 # JPA Entity Classes
│   │   │       │   ├── User.java
│   │   │       │   ├── Course.java
│   │   │       │   ├── Question.java
│   │   │       │   ├── CodingProblem.java
│   │   │       │   ├── Test.java
│   │   │       │   └── TestResult.java
│   │   │       ├── repository/             # Data Access Interfaces
│   │   │       │   ├── UserRepository.java
│   │   │       │   ├── CourseRepository.java
│   │   │       │   ├── QuestionRepository.java
│   │   │       │   ├── CodingProblemRepository.java
│   │   │       │   ├── TestRepository.java
│   │   │       │   └── TestResultRepository.java
│   │   │       ├── service/                # Business Logic Services
│   │   │       │   ├── UserService.java
│   │   │       │   ├── CourseService.java
│   │   │       │   ├── CodingService.java
│   │   │       │   └── TestService.java
│   │   │       ├── util/                   # Utility Classes
│   │   │       │   └── JwtUtil.java
│   │   │       ├── dto/                    # Data Transfer Objects
│   │   │       │   └── LoginRequest.java
│   │   │       └── InterviewPortalApplication.java  # Main Application Class
│   │   └── resources/
│   │       └── application.properties      # Spring Boot Configuration
│   └── test/
│       └── java/com/interview/portal/      # Unit Tests
└── pom.xml                                 # Maven Configuration

```

### Backend File Descriptions

#### Configuration Classes (config/)

**SecurityConfig.java**
- Configures Spring Security for the application
- Sets up JWT authentication filter
- Configures CORS (Cross-Origin Resource Sharing)
- Defines authorization rules for endpoints
- Configures password encoder (BCrypt)

**JwtAuthenticationFilter.java**
- Custom filter for JWT token validation
- Extracts JWT token from Authorization header
- Validates token and sets authentication context
- Allows public endpoints to bypass authentication

**CorsConfig.java**
- Configures CORS settings for frontend-backend communication
- Defines allowed origins, methods, and headers
- Sets credentials and max age for preflight requests

#### Entity Classes (entity/)

**User.java**
- Represents user account information
- Fields: id, email, password, name, department, year, role, status
- Enums: UserRole (ADMIN, STUDENT), UserStatus (PENDING, APPROVED, REJECTED)
- Timestamps: createdAt, updatedAt

**Course.java**
- Represents a course in the platform
- Fields: id, name, description, totalQuestions
- Timestamps: createdAt, updatedAt

**Question.java**
- Represents MCQ questions associated with courses
- Fields: id, courseId, questionText, optionA-D, correctAnswer, explanation
- Relationship: Many-to-One with Course

**CodingProblem.java**
- Represents coding practice problems
- Fields: id, title, description, difficulty, category, sampleInput, sampleOutput, constraints
- Enum: DifficultyLevel (EASY, MEDIUM, HARD)
- Timestamps: createdAt, updatedAt

**Test.java**
- Represents mock tests
- Fields: id, title, description, durationMinutes, totalQuestions, passingScore, isActive
- Timestamps: createdAt, updatedAt

**TestResult.java**
- Records test submission results
- Fields: id, userId, testId, score, correctAnswers, wrongAnswers, skippedQuestions, percentage, status, timeTaken
- Enum: ResultStatus (PASSED, FAILED, IN_PROGRESS)
- Relationships: Many-to-One with User and Test

#### Repository Interfaces (repository/)

**UserRepository.java**
- Extends JpaRepository for User entity
- Custom methods: findByEmail, findByRole, findByStatus, existsByEmail

**CourseRepository.java**
- Extends JpaRepository for Course entity
- Custom methods: findByName, findAll

**QuestionRepository.java**
- Extends JpaRepository for Question entity
- Custom methods: findByCourse, findByCourseId, countByCourseId

**CodingProblemRepository.java**
- Extends JpaRepository for CodingProblem entity
- Custom methods: findByDifficulty, findByCategory, findByCategoryAndDifficulty

**TestRepository.java**
- Extends JpaRepository for Test entity
- Custom methods: findByTitle, findByIsActive

**TestResultRepository.java**
- Extends JpaRepository for TestResult entity
- Custom methods: findByUser, findByTest, findByUserIdOrderByCompletedAtDesc

#### Service Classes (service/)

**UserService.java**
- Business logic for user management
- Methods: registerUser, loginUser, getUserById, approveUser, rejectUser, deleteUser

**CourseService.java**
- Business logic for course management
- Methods: getAllCourses, getCourseById, createCourse, updateCourse, deleteCourse, getCourseQuestions

**CodingService.java**
- Business logic for coding problems
- Methods: getAllProblems, getProblemById, getProblemsByDifficulty, createProblem, updateProblem, deleteProblem

**TestService.java**
- Business logic for test management
- Methods: getAllTests, getTestById, createTest, submitTestResult, getUserTestResults

#### Controller Classes (controller/)

**AuthController.java**
- Handles authentication endpoints
- Endpoints: POST /register, POST /login, GET /validate-token

**CourseController.java**
- Handles course-related endpoints
- Endpoints: GET/POST /courses, GET/PUT/DELETE /courses/{id}, GET/POST /courses/{courseId}/questions

**CodingController.java**
- Handles coding problem endpoints
- Endpoints: GET/POST /coding/problems, GET /coding/problems/{id}, GET /coding/problems/difficulty/{difficulty}

**TestController.java**
- Handles test-related endpoints
- Endpoints: GET /tests, POST /tests/{testId}/submit, GET /tests/user/results

**AdminController.java**
- Handles admin-specific endpoints
- Endpoints: GET /admin/users, GET /admin/pending-approvals, PUT /admin/users/{userId}/approve, DELETE /admin/users/{userId}

#### Utility Classes (util/)

**JwtUtil.java**
- JWT token generation and validation
- Methods: generateToken, validateToken, getEmailFromToken, getRoleFromToken, isTokenExpired

#### Data Transfer Objects (dto/)

**LoginRequest.java**
- DTO for login request containing email and password

#### Main Application Class

**InterviewPortalApplication.java**
- Entry point for Spring Boot application
- Contains main method to start the application

#### Configuration Files

**application.properties**
- Spring Boot configuration settings
- Database connection details
- JPA/Hibernate configuration
- JWT configuration
- Logging configuration

## Frontend Directory Structure

```
frontend/
├── index.html                  # Landing Page
├── pages/                      # HTML Pages
│   ├── login.html             # User Login Page
│   ├── register.html          # User Registration Page
│   ├── dashboard.html         # Student Dashboard
│   ├── courses.html           # Courses Page
│   ├── coding.html            # Coding Practice Page
│   ├── tests.html             # Mock Tests Page
│   └── admin.html             # Admin Control Panel
├── css/                        # Stylesheets
│   └── styles.css             # Main Responsive Stylesheet
├── js/                         # JavaScript Files
│   └── app.js                 # Utilities and API Integration
└── assets/                     # Static Assets (Images, Icons)
```

### Frontend File Descriptions

#### HTML Pages

**index.html**
- Landing page with hero section and feature overview
- Navigation bar with links to login and register
- Call-to-action section encouraging user registration

**login.html**
- User login form with email and password fields
- Form validation and error handling
- Redirect to dashboard or admin panel based on user role

**register.html**
- User registration form with fields for name, email, password, department, and year
- Form validation and error handling
- Redirect to login page after successful registration

**dashboard.html**
- Student dashboard showing user information
- Quick links to courses, coding problems, and tests
- Recent activity section
- Statistics cards showing available resources

**courses.html**
- List of available courses
- Course cards with descriptions and question counts
- Modal-based quiz interface for taking course quizzes
- Question navigation and answer submission

**coding.html**
- List of coding problems with difficulty levels
- Filter by difficulty level
- Integrated code editor for writing solutions
- Sample input/output display
- Code execution and submission functionality

**tests.html**
- List of available mock tests
- Test details including duration and question count
- Test interface with timer and question navigation
- Results display with score and performance metrics

**admin.html**
- Admin dashboard with statistics
- User management table
- Pending approvals list
- User approval and rejection functionality
- User deletion capability

#### Stylesheets

**styles.css**
- Global styles for all pages
- Responsive design with media queries
- Navigation bar styling
- Form styling and validation
- Button and card component styles
- Modal and dialog styling
- Table styling for admin panel
- Mobile-first responsive design

#### JavaScript Files

**app.js**
- APIClient class for HTTP requests
- AuthService for authentication operations
- CourseService for course-related operations
- CodingService for coding problem operations
- TestService for test-related operations
- AdminService for admin operations
- UIHelper for UI-related utilities
- Validator for form validation
- StorageHelper for localStorage operations

## Database Directory Structure

```
database/
├── schema.sql                  # Database Schema
└── sample_data.sql            # Sample Test Data
```

### Database File Descriptions

**schema.sql**
- Creates interview_portal database
- Creates users table with role and status enums
- Creates courses table
- Creates questions table with foreign key to courses
- Creates coding_problems table with difficulty enum
- Creates tests table
- Creates test_results table with foreign keys to users and tests
- Creates indexes for performance optimization

**sample_data.sql**
- Inserts admin user (admin@portal.com)
- Inserts sample student users
- Inserts sample courses
- Inserts sample questions for courses
- Inserts sample coding problems
- Inserts sample tests
- Inserts sample test results

## Documentation Files

```
docs/
├── README.md                   # Project Overview
├── SETUP.md                    # Setup Instructions
└── PROJECT_STRUCTURE.md        # This File
```

## Configuration and Build Files

**pom.xml** (Backend)
- Maven project configuration
- Dependencies: Spring Boot, Spring Security, JWT, MySQL, Lombok
- Build plugins and configurations

## File Naming Conventions

### Java Files
- Classes: PascalCase (e.g., UserService.java)
- Packages: lowercase (e.g., com.interview.portal)
- Constants: UPPER_SNAKE_CASE

### HTML Files
- Lowercase with hyphens for multi-word names (e.g., login.html)
- Descriptive names indicating page purpose

### CSS Files
- Lowercase with hyphens (e.g., styles.css)
- One main stylesheet for simplicity

### JavaScript Files
- Lowercase with hyphens (e.g., app.js)
- Organized by functionality

## Key Design Patterns

### Backend Patterns

**MVC Pattern**
- Models: Entity classes
- Views: JSON responses from controllers
- Controllers: REST API endpoints

**Service Layer Pattern**
- Business logic separated from controllers
- Reusable service methods
- Dependency injection via Spring

**Repository Pattern**
- Data access abstraction
- JPA repositories for database operations
- Custom query methods

### Frontend Patterns

**Service Classes**
- Encapsulate API communication
- Provide methods for backend operations
- Handle error responses

**Utility Classes**
- Reusable helper functions
- UI utilities for common operations
- Form validation utilities

## Dependencies and Versions

### Backend Dependencies
- Spring Boot 2.7.14
- Spring Security
- Spring Data JPA
- MySQL Connector 8.0.33
- JWT (jjwt) 0.11.5
- Lombok (optional)

### Frontend Dependencies
- HTML5
- CSS3
- JavaScript ES6+
- Fetch API (built-in)

## Scalability Considerations

### Backend Scalability
- Horizontal scaling with load balancing
- Database connection pooling
- Caching mechanisms
- Microservices architecture potential

### Frontend Scalability
- Component-based architecture potential
- Lazy loading for performance
- Service worker for offline support
- CDN integration for static assets

## Security Considerations

### Backend Security
- JWT token-based authentication
- Role-based access control (RBAC)
- Password hashing with BCrypt
- CORS configuration
- Input validation and sanitization

### Frontend Security
- Client-side form validation
- Secure token storage in localStorage
- HTTPS for production
- XSS prevention through proper escaping

## Performance Optimization

### Backend Optimization
- Database indexing
- Query optimization
- Connection pooling
- Caching strategies

### Frontend Optimization
- Minified CSS and JavaScript
- Lazy loading of resources
- Image optimization
- Efficient DOM manipulation

## Maintenance and Updates

### Regular Tasks
- Update dependencies
- Monitor application logs
- Optimize database queries
- Review security patches
- Backup database regularly

### Code Quality
- Follow naming conventions
- Write meaningful comments
- Keep functions small and focused
- Use appropriate design patterns
- Maintain consistent code style

## Future Enhancement Opportunities

1. **Real-time Features**
   - WebSocket for live notifications
   - Real-time collaboration features

2. **Advanced Analytics**
   - Performance dashboards
   - Learning path recommendations
   - Detailed progress tracking

3. **Mobile Applications**
   - Native Android app
   - Native iOS app
   - Progressive Web App (PWA)

4. **Integration Features**
   - Third-party authentication (OAuth)
   - Integration with coding judges
   - Video tutorial integration

5. **Gamification**
   - Achievement badges
   - Leaderboards
   - Point systems
   - Streak tracking

## Conclusion

This project structure provides a solid foundation for a scalable, maintainable interview preparation platform. The separation of concerns, clear naming conventions, and organized file structure make it easy to understand, modify, and extend the application.
