// Interview Portal - JavaScript Utilities and API Integration

// API Configuration
const API_BASE_URL = 'http://localhost:8080/api';

// Utility Functions
class APIClient {
    constructor(baseURL) {
        this.baseURL = baseURL;
    }

    getToken() {
        return localStorage.getItem('token');
    }

    getHeaders() {
        const headers = {
            'Content-Type': 'application/json'
        };
        const token = this.getToken();
        if (token) {
            headers['Authorization'] = `Bearer ${token}`;
        }
        return headers;
    }

    async request(endpoint, options = {}) {
        const url = `${this.baseURL}${endpoint}`;
        const config = {
            headers: this.getHeaders(),
            ...options
        };

        try {
            const response = await fetch(url, config);
            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || 'API request failed');
            }

            return data;
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    }

    get(endpoint) {
        return this.request(endpoint, { method: 'GET' });
    }

    post(endpoint, body) {
        return this.request(endpoint, {
            method: 'POST',
            body: JSON.stringify(body)
        });
    }

    put(endpoint, body) {
        return this.request(endpoint, {
            method: 'PUT',
            body: JSON.stringify(body)
        });
    }

    delete(endpoint) {
        return this.request(endpoint, { method: 'DELETE' });
    }
}

// Initialize API Client
const api = new APIClient(API_BASE_URL);

// Authentication Functions
class AuthService {
    static async login(email, password) {
        return api.post('/auth/login', { email, password });
    }

    static async register(user) {
        return api.post('/auth/register', user);
    }

    static async validateToken() {
        const token = localStorage.getItem('token');
        if (!token) return null;

        try {
            return await api.get('/auth/validate-token');
        } catch (error) {
            localStorage.removeItem('token');
            localStorage.removeItem('user');
            return null;
        }
    }

    static logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
    }

    static getUser() {
        const userStr = localStorage.getItem('user');
        return userStr ? JSON.parse(userStr) : null;
    }

    static setUser(user) {
        localStorage.setItem('user', JSON.stringify(user));
    }

    static isAuthenticated() {
        return !!localStorage.getItem('token');
    }

    static isAdmin() {
        const user = this.getUser();
        return user && user.role === 'ADMIN';
    }
}

// Course Service
class CourseService {
    static async getAllCourses() {
        return api.get('/courses');
    }

    static async getCourseById(id) {
        return api.get(`/courses/${id}`);
    }

    static async getCourseQuestions(courseId) {
        return api.get(`/courses/${courseId}/questions`);
    }

    static async createCourse(course) {
        return api.post('/courses', course);
    }

    static async updateCourse(id, course) {
        return api.put(`/courses/${id}`, course);
    }

    static async deleteCourse(id) {
        return api.delete(`/courses/${id}`);
    }

    static async addQuestion(courseId, question) {
        return api.post(`/courses/${courseId}/questions`, question);
    }
}

// Coding Service
class CodingService {
    static async getAllProblems() {
        return api.get('/coding/problems');
    }

    static async getProblemById(id) {
        return api.get(`/coding/problems/${id}`);
    }

    static async getProblemsByDifficulty(difficulty) {
        return api.get(`/coding/problems/difficulty/${difficulty}`);
    }

    static async getProblemsByCategory(category) {
        return api.get(`/coding/problems/category/${category}`);
    }

    static async createProblem(problem) {
        return api.post('/coding/problems', problem);
    }

    static async updateProblem(id, problem) {
        return api.put(`/coding/problems/${id}`, problem);
    }

    static async deleteProblem(id) {
        return api.delete(`/coding/problems/${id}`);
    }
}

// Test Service
class TestService {
    static async getAllTests() {
        return api.get('/tests');
    }

    static async getActiveTests() {
        return api.get('/tests/active');
    }

    static async getTestById(id) {
        return api.get(`/tests/${id}`);
    }

    static async createTest(test) {
        return api.post('/tests', test);
    }

    static async updateTest(id, test) {
        return api.put(`/tests/${id}`, test);
    }

    static async deleteTest(id) {
        return api.delete(`/tests/${id}`);
    }

    static async submitTest(testId, submissionData) {
        return api.post(`/tests/${testId}/submit`, submissionData);
    }

    static async getUserTestResults() {
        return api.get('/tests/user/results');
    }
}

// Admin Service
class AdminService {
    static async getAllUsers() {
        return api.get('/admin/users');
    }

    static async getAllStudents() {
        return api.get('/admin/students');
    }

    static async getPendingApprovals() {
        return api.get('/admin/pending-approvals');
    }

    static async approveUser(userId) {
        return api.put(`/admin/users/${userId}/approve`, {});
    }

    static async rejectUser(userId) {
        return api.put(`/admin/users/${userId}/reject`, {});
    }

    static async deleteUser(userId) {
        return api.delete(`/admin/users/${userId}`);
    }

    static async getStats() {
        return api.get('/admin/stats');
    }
}

// UI Helper Functions
class UIHelper {
    static showMessage(message, type = 'info') {
        const messageDiv = document.getElementById('message');
        if (messageDiv) {
            messageDiv.textContent = message;
            messageDiv.className = `message ${type}`;
            messageDiv.style.display = 'block';
        }
    }

    static showError(message) {
        this.showMessage(message, 'error');
    }

    static showSuccess(message) {
        this.showMessage(message, 'success');
    }

    static showLoading(show = true) {
        const loader = document.getElementById('loader');
        if (loader) {
            loader.style.display = show ? 'block' : 'none';
        }
    }

    static formatDate(date) {
        return new Date(date).toLocaleDateString();
    }

    static formatTime(seconds) {
        const hours = Math.floor(seconds / 3600);
        const minutes = Math.floor((seconds % 3600) / 60);
        const secs = seconds % 60;

        if (hours > 0) {
            return `${hours}h ${minutes}m ${secs}s`;
        }
        return `${minutes}m ${secs}s`;
    }

    static calculatePercentage(obtained, total) {
        return ((obtained / total) * 100).toFixed(2);
    }
}

// Validation Functions
class Validator {
    static isValidEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    static isValidPassword(password) {
        return password && password.length >= 6;
    }

    static isValidName(name) {
        return name && name.trim().length >= 2;
    }

    static validateLoginForm(email, password) {
        if (!this.isValidEmail(email)) {
            throw new Error('Invalid email address');
        }
        if (!this.isValidPassword(password)) {
            throw new Error('Password must be at least 6 characters');
        }
        return true;
    }

    static validateRegisterForm(name, email, password, department, year) {
        if (!this.isValidName(name)) {
            throw new Error('Name must be at least 2 characters');
        }
        if (!this.isValidEmail(email)) {
            throw new Error('Invalid email address');
        }
        if (!this.isValidPassword(password)) {
            throw new Error('Password must be at least 6 characters');
        }
        if (!department) {
            throw new Error('Please select a department');
        }
        if (!year) {
            throw new Error('Please select year of study');
        }
        return true;
    }
}

// Storage Helper
class StorageHelper {
    static setItem(key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    }

    static getItem(key) {
        const item = localStorage.getItem(key);
        return item ? JSON.parse(item) : null;
    }

    static removeItem(key) {
        localStorage.removeItem(key);
    }

    static clear() {
        localStorage.clear();
    }
}

// Export for use in other files
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        api,
        AuthService,
        CourseService,
        CodingService,
        TestService,
        AdminService,
        UIHelper,
        Validator,
        StorageHelper
    };
}
