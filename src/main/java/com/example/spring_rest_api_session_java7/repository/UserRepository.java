package com.example.spring_rest_api_session_java7.repository;

import com.example.spring_rest_api_session_java7.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> finByEmail (String email);
}
