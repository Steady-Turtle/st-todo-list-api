package com.example.anna.entity.user.repository;

import com.example.anna.entity.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>,UserRepositoryCustom {

    Optional<User> findByEmail(String email);

}
