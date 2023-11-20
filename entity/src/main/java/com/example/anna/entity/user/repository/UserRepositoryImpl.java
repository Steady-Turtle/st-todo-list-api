package com.example.anna.entity.user.repository;

import com.example.anna.entity._common.DomainEntityNotFoundException;
import com.example.anna.entity.user.domain.QUser;
import com.example.anna.entity.user.domain.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User getByUserIdAndEmail(String userId, String email) {
        return Optional.ofNullable(
                from(QUser.user).where(QUser.user.email.eq(email), QUser.user.userId.eq(userId))
                        .select(QUser.user)
                        .fetchOne()
        ).orElseThrow(() -> new DomainEntityNotFoundException(User.class,"USER_NOT_FOUND"));


    }

    @Override
    public User getByUserId(String userId) {
        return Optional.ofNullable(
                from(QUser.user).where( QUser.user.userId.eq(userId))
                        .select(QUser.user)
                        .fetchOne()
        ).orElseThrow(() -> new DomainEntityNotFoundException(User.class,"USER_NOT_FOUND"));


    }

}
