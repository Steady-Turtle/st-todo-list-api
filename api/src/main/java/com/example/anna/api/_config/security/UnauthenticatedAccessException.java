package com.example.anna.api._config.security;


import com.example.anna.common._common.util.MessageUtil;

public class UnauthenticatedAccessException extends RuntimeException {

    public UnauthenticatedAccessException() {
        super(MessageUtil.getMessage("UNAUTHENTICATED_USER"));
    }
}
