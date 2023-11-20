package com.example.anna.api._support.exception;


import com.example.anna.common._support.exception.*;
import com.example.anna.entity._common.DomainEntityNotFoundException;
import com.example.anna.api._config.security.UnauthenticatedAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 비즈니스 검증 에러 출력 & 리턴 값 전달시 사용
     *
     * @param e
     * @param principal
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BizException.class})
    public BizApiError handleBusinessInvalidException(BizException e, @Nullable Principal principal) {
        BizApiError apiError = new BizApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now().toString(),
                e.toString(),e.getWarningMessage(), e.getClass().getSimpleName(), e.getReturnMap());

        return apiError;
    }


    /**
     * 밸리데이션 값 검증 에러
     * @param e
     * @param principal
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidException.class, InvalidParameterException.class})
    public ApiError handleInvaliduserException(InvalidException e, @Nullable Principal principal) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now().toString(),
                e.toString(), e.getMessage(), e.getClass().getSimpleName());

        if (e.getErrors() != null)
            apiError.setFieldErrors(e.getErrors().getFieldErrors());

        return apiError;
    }

    /**
     * UNAUTHORIZED 에러
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({ UnauthenticatedAccessException.class, BadCredentialsException.class })
    public ApiError handleUnauthenticatiedException(Exception e) {
        log(e);
        return new ApiError(HttpStatus.UNAUTHORIZED, LocalDateTime.now().toString(),
                e.toString(), e.getMessage(), e.getClass().getSimpleName());
    }

    /**
     * UNAUTHORIZED 에러
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ AccessDeniedException.class })
    public ApiError handle(Exception e) {
        log(e);
        return new ApiError(HttpStatus.FORBIDDEN, LocalDateTime.now().toString(),
                e.toString(), e.getMessage(), e.getClass().getSimpleName());
    }


    /**
     * 도메인익셉션
     * @param e
     * @param principal
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DomainEntityNotFoundException.class)
    public ApiError handleDomainException(DomainEntityNotFoundException e, @Nullable Principal principal) {
        log(e, e.getWarningMessage(), principal);
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now().toString(),
                e.toString(), e.getWarningMessage(), e.getEntityClazz());
    }

    /**
     * 그 외 사용자 정의 에러 & 서버 에러
     * @param e
     * @param principal
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomException.class)
    public ApiError handleDefaultException(CustomException e, @Nullable Principal principal) {
        log(e, e.getWarningMessage(), principal);
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now().toString(),
                e.toString(), e.getWarningMessage(), e.getClass().getSimpleName());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError handleDefaultException(Exception e, @Nullable Principal principal) {
        log(e, principal);

        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now().toString(),
                e.toString(), e.getClass().getSimpleName());
    }

    private void log(Exception e) {
        log.error("{} | {}", now(), e.toString());
    }

    private void log(Exception e, @Nullable Principal principal) {
        String userId = "";
        if(principal != null)
            userId = principal.getName();

        log.error("{} | userId : {} | {}", now(), userId, e.toString());
    }

    private void log(Exception e, String warningMessage, @Nullable Principal principal) {
        String userId = "";
        if(principal != null)
            userId = principal.getName();

        log.error("{} | userId : {} | Alert Message: {} | {}", now(), userId, warningMessage, e.toString());
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
