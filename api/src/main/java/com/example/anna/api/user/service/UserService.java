package com.example.anna.api.user.service;

import com.example.anna.entity.user.domain.User;
import com.example.anna.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User findBydUserId(String userId) {
        return userRepository.getByUserId(userId);
    }

    /**
     * 비밀번호 변경
     *
     * @param userId
     * @param newPassword
     * @return
     */
    @Transactional
    public User changePassword(String userId, String newPassword) {
        User user = findBydUserId(userId);
        user.changePassword(passwordEncoder.encode(newPassword));
        return user;
    }

    /**
     * 회원탈퇴
     *
     * @param userId
     */
    @Transactional
    public void withdrawUser(String userId) {
        User user = findBydUserId(userId);
        user.withdrawUser();

    }

}
