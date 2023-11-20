package com.example.anna.api.user.exception;

import com.example.anna.common._support.exception.InvalidException;
import lombok.Getter;
import org.springframework.validation.Errors;

/**
 * 사용자 정보 에러처리
 */
@Getter
public class InvalidUserInfoException extends InvalidException {
    public InvalidUserInfoException(String message, Errors errors) {
        super(message, errors);
    }

    public InvalidUserInfoException(String message) {
        super(message);
    }
}
