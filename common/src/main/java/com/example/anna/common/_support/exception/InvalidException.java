package com.example.anna.common._support.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

/**
 * 밸리데이션 에러처리
 */
@Getter
public class InvalidException extends RuntimeException {

    private Errors errors;

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }
}
