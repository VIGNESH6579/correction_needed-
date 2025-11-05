# Interview Portal - Setup Instructions

This guide provides step-by-step instructions to set up and run the Interview Portal application on your local machine.

## Prerequisites

Before starting the setup, ensure you have the following installed on your system:

- **Java Development Kit (JDK)** - Version 11 or higher
- **Maven** - Version 3.6 or higher (for building the backend)
- **MySQL Server** - Version 8.0 or higher
- **Git** - For version control
- **A modern web browser** - Chrome, Firefox, Safari, or Edge

## Backend Setup (Spring Boot)

### Step 1: Install Java and Maven

**For Windows:**
1. Download JDK 11 or higher from Oracle's website
2. Install JDK and set JAVA_HOME environment variable
3. Download Maven from apache.org and extract it
4. Add Maven bin directory to PATH environment variable

**For macOS:**
```bash
# Using Homebrew
brew install openjdk@11
brew install maven
```

**For Linux (Ubuntu/Debian):**
```bash
sudo apt-get update
sudo apt-get install openjdk-11-jdk
sudo apt-get install maven
```

### Step 2: Verify Java and Maven Installation

```bash
java -version
mvn -version
```

### Step 3: Configure MySQL Database

**For Windows:**
1. Download MySQL Community Server from mysql.com
2. Run the installer and follow the setup wizard
3. Note the port (default 3306) and root password

**For macOS:**
```bash
brew install mysql
brew services start mysql
mysql_secure_installation
```

**For Linux (Ubuntu/Debian):**
```bash
sudo apt-get install mysql-server
sudo mysql_secure_installation
```

### Step 4: Create Database and Load Schema

```bash
# Connect to MySQL
mysql -u root -p

# Create database (in MySQL prompt)
CREATE DATABASE interview_portal;
USE interview_portal;

# Load schema
SOURCE /path/to/database/schema.sql;

# Load sample data
SOURCE /path/to/database/sample_data.sql;

# Verify installation
SHOW TABLES;
```

### Step 5: Configure Backend Application

1. Navigate to the backend directory:
```bash
cd interview-portal-complete/backend
```

2. Update `src/main/resources/application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/interview_portal?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Update JWT secret (optional but recommended for production):
```properties
jwt.secret=your-secure-secret-key-here
```

### Step 6: Build and Run Backend

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

You should see output similar to:
```
Started InterviewPortalApplication in 5.234 seconds
```

## Frontend Setup

### Step 1: Configure API URL

The frontend is configured to connect to `http://localhost:8080/api` by default. If your backend is running on a different URL, update the API_BASE_URL in `frontend/js/app.js`:

```javascript
const API_BASE_URL = 'http://your-backend-url/api';
```

### Step 2: Serve Frontend Files

**Option 1: Using Python (Recommended for Development)**

```bash
# Navigate to frontend directory
cd interview-portal-complete/frontend

# Python 3
python -m http.server 5500

# Python 2
python -m SimpleHTTPServer 5500
```

**Option 2: Using Node.js**

```bash
# Install http-server globally
npm install -g http-server

# Navigate to frontend directory
cd interview-portal-complete/frontend

# Start server
http-server -p 5500
```

**Option 3: Using Live Server (VS Code Extension)**

1. Install "Live Server" extension in VS Code
2. Right-click on index.html and select "Open with Live Server"

The frontend will be available at `http://localhost:5500`

## Verification Checklist

After setup, verify that everything is working correctly:

- [ ] MySQL database is running and contains all tables
- [ ] Backend Spring Boot application is running on port 8080
- [ ] Frontend is accessible on port 5500
- [ ] Can access landing page at `http://localhost:5500`
- [ ] Can register a new user
- [ ] Can login with admin credentials (admin@portal.com / Admin@123)
- [ ] Can access admin panel as admin user
- [ ] Can access student dashboard as student user
- [ ] Can view courses and coding problems
- [ ] Can take mock tests

## Troubleshooting

### Backend Issues

**Problem: Port 8080 already in use**
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>
```

**Problem: MySQL connection refused**
- Verify MySQL is running: `sudo systemctl status mysql`
- Check credentials in application.properties
- Ensure database exists: `mysql -u root -p -e "SHOW DATABASES;"`

**Problem: Maven build fails**
```bash
# Clear Maven cache
mvn clean
mvn install
```

### Frontend Issues

**Problem: CORS errors in browser console**
- Ensure backend is running on http://localhost:8080
- Check CORS configuration in SecurityConfig.java
- Verify API_BASE_URL in app.js is correct

**Problem: Blank page or 404 errors**
- Clear browser cache (Ctrl+Shift+Delete)
- Verify frontend server is running on correct port
- Check browser console for JavaScript errors

**Problem: Login not working**
- Verify backend is running
- Check network tab in browser dev tools
- Ensure database has sample users

## Production Deployment

For production deployment, follow these additional steps:

### Backend Production Setup

1. **Change default credentials:**
   - Update admin and student passwords in database
   - Generate secure JWT secret key

2. **Update CORS origins:**
   - Modify SecurityConfig.java to include production domain
   - Remove localhost from allowed origins

3. **Database optimization:**
   - Enable SSL for MySQL connections
   - Set up automated backups
   - Configure connection pooling

4. **Build JAR file:**
```bash
mvn clean package
java -jar target/interview-portal-1.0.0.jar
```

### Frontend Production Setup

1. **Minify and optimize:**
   - Minify CSS and JavaScript
   - Optimize images
   - Enable gzip compression

2. **Update API URL:**
   - Change API_BASE_URL to production backend URL
   - Implement environment-specific configuration

3. **Deploy to web server:**
   - Use Apache, Nginx, or cloud hosting
   - Configure HTTPS/SSL certificates
   - Set up CDN for static assets

## Performance Optimization

### Backend Optimization

- Enable query result caching
- Implement pagination for large datasets
- Use database connection pooling
- Add indexes to frequently queried columns

### Frontend Optimization

- Implement lazy loading for images
- Use service workers for offline support
- Minify CSS and JavaScript
- Implement client-side caching

## Security Recommendations

1. **Change default credentials** before deploying to production
2. **Use HTTPS** for all communications
3. **Implement rate limiting** to prevent brute force attacks
4. **Keep dependencies updated** to patch security vulnerabilities
5. **Use environment variables** for sensitive configuration
6. **Implement input validation** on both frontend and backend
7. **Use prepared statements** to prevent SQL injection
8. **Enable CORS only for trusted origins** in production

## Support

If you encounter any issues during setup, please:

1. Check the troubleshooting section above
2. Review application logs for error messages
3. Verify all prerequisites are installed correctly
4. Check that all configuration files are properly updated

## Next Steps

After successful setup:

1. Explore the admin panel to manage users and content
2. Create additional courses and coding problems
3. Test the platform with multiple user accounts
4. Customize branding and styling as needed
5. Set up automated backups for the database
6. Plan for scaling and performance optimization

## Additional Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- MySQL Documentation: https://dev.mysql.com/doc/
- JWT Documentation: https://jwt.io/
- MDN Web Docs: https://developer.mozilla.org/

## Maintenance

### Regular Maintenance Tasks

- Monitor application logs for errors
- Back up database regularly
- Update dependencies periodically
- Review and optimize database queries
- Monitor server performance and resource usage
- Update security patches promptly

### Database Maintenance

```bash
# Optimize tables
OPTIMIZE TABLE users;
OPTIMIZE TABLE courses;
OPTIMIZE TABLE questions;
OPTIMIZE TABLE test_results;

# Check table integrity
CHECK TABLE users;
CHECK TABLE courses;
```

## Conclusion

Your Interview Portal is now set up and ready to use. For any questions or issues, refer to the README.md and PROJECT_STRUCTURE.md files for additional information.
