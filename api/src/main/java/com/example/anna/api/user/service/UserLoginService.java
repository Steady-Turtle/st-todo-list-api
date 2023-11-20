package com.example.anna.api.user.service;

import com.example.anna.api._config.security.jwt.JwtAuthenticationResponse;
import com.example.anna.api._config.security.jwt.JwtTokenProvider;
import com.example.anna.api.user.exception.InvalidUserException;
import com.example.anna.api.user.exception.UserLockException;
import com.example.anna.api.user.exception.UserNotFoundException;
import com.example.anna.common._common.util.MessageUtil;
import com.example.anna.api._config.security.UnauthenticatedAccessException;
import com.example.anna.api._config.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationResponse login(String userId, String password) {
        UserPrincipal userPrincipal;
        Authentication authentication;

        try {
            authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userId,
                            password
                    )
            );

        } catch (InternalAuthenticationServiceException e) { // 존재하지 않는 사용자
            throw new UserNotFoundException();
        } catch (DisabledException e) {  // 유효한 회원이 아님
            throw new InvalidUserException(MessageUtil.getMessage("LOGIN_FAIL"));
        } catch (LockedException e) {    // 계정 잠김
            throw new UserLockException(userId, MessageUtil.getMessage("REQUIRED_CAPTCHA"));
        } catch (UnauthenticatedAccessException e) {
            throw new UnauthenticatedAccessException();
        }

        userPrincipal = (UserPrincipal) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.tokenProvider.generateToken(authentication);

        return new JwtAuthenticationResponse(jwt, userId, Set.of(userPrincipal.getRole()));
    }

}
