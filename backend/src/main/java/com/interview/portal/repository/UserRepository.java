package com.interview.portal.repository;

import com.interview.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(User.UserRole role);
    List<User> findByStatus(User.UserStatus status);
    List<User> findByRoleAndStatus(User.UserRole role, User.UserStatus status);
    boolean existsByEmail(String email);
}
