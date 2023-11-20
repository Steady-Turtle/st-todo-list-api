package com.example.anna.api.user.exception;

import com.example.anna.common._support.exception.CustomException;
import lombok.Getter;

/**
 * 사용자 정보 에러처리
 */
@Getter
public class InvalidUserException extends CustomException {
    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String warningMessage) {
        super(warningMessage);
    }
}
