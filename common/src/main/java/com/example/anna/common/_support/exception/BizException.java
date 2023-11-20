package com.example.anna.common._support.exception;

import lombok.Getter;

import java.util.Map;

/**
 * 사용자 비즈니스 익셉션
 */
@Getter
public class BizException extends RuntimeException {

    private String warningMessage;

    private Map<String, Object> returnMap;

    private String addlog;

}
