package com.example.anna.entity.user.repository;


import com.example.anna.entity.user.domain.User;


public interface UserRepositoryCustom {

    User getByUserIdAndEmail(String userId, String email);

    User getByUserId(String userId);
}
