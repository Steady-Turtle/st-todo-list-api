package com.example.anna.common._support.exception;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiError {

    @NonNull
    private HttpStatus status;

    @NonNull
    private String timestamp;

    private String exceptionName;

    private String debugMessage;

    private String warningMessage = "서버에서 에러가 발생했습니다.";

    private List<FieldError> fieldErrors;

    public ApiError(@NonNull HttpStatus status, @NonNull String timestamp, String debugMessage,
                    String warningMessage, String exceptionName) {
        this.status = status;
        this.timestamp = timestamp;
        this.debugMessage = debugMessage;
        this.warningMessage = warningMessage;
        this.exceptionName = exceptionName;
    }

    public ApiError(@NonNull HttpStatus status, @NonNull String timestamp, String debugMessage,
                    String exceptionName) {
        this.status = status;
        this.timestamp = timestamp;
        this.debugMessage = debugMessage;
        this.exceptionName = exceptionName;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
