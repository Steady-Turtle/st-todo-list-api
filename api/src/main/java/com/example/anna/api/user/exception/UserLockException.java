package com.example.anna.api.user.exception;


import com.example.anna.common._support.exception.CustomException;

/**
 * 사용자 잠김 예외처리
 */
public class UserLockException extends CustomException {

    public UserLockException(String message, String warningMessage) {
        super(message, warningMessage);
    }
}
