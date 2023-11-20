package com.example.anna.common._support.exception;

import lombok.Getter;

/**
 * Class Description
 */
@Getter
public class CustomException extends RuntimeException {
    private String warningMessage;

    public CustomException() {
        super();
    }

    public CustomException(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public CustomException(String message, String warningMessage) {
        super(message);
        this.warningMessage = warningMessage;
    }
}
