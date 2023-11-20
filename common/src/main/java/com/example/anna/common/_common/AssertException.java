package com.example.anna.common._common;


import com.example.anna.common._support.exception.CustomException;

public class AssertException extends CustomException {

    public AssertException() {
        super();
    }

    public AssertException(String warningMessage) {
        super(warningMessage);
    }

    public AssertException(String message, String warningMessage) {
        super(message, warningMessage);
    }
}
