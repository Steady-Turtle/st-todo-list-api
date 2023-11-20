package com.example.anna.api.user.service;

import com.example.anna.api.user.exception.InvalidUserInfoException;
import com.example.anna.common._common.util.MessageUtil;
import com.example.anna.entity.user.domain.User;
import com.example.anna.entity.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRegisterService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     * 회원가입
     *
     * @param user
     * @return
     */
    @Transactional
    public User register(User user) {
        checkDuplicateId(user.getUserId());
        checkDuplicateEmail(user.getEmail());
        user.changePassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }


    /**
     * 아이디 중복 확인
     *
     * @param userId
     */
    private void checkDuplicateId(String userId) {
        if (isUseUserId(userId)) {
            throw new InvalidUserInfoException(MessageUtil.getMessage("USERID_ALREADY_REGISTERED"));
        }
    }

    public boolean isUseUserId(String userId) {
        return this.userRepository.findById(userId).isPresent();
    }


    /**
     * 이메일 중복 확인
     *
     * @param email
     */
    private void checkDuplicateEmail(String email) {
        if (isUseUserId(email)) {
            throw new InvalidUserInfoException(MessageUtil.getMessage("EMAIL_ALREADY_REGISTERED"));
        }
    }

    public boolean isUseUserEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    public Boolean isUse(String type, String value) {
        if(type.equals("userId"))
            return !isUseUserId(value);
        else if(type.equals("email"))
            return !isUseUserEmail(value);

        return true;
    }

}
