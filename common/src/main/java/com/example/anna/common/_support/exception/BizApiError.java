package com.example.anna.common._support.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.util.Map;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BizApiError extends ApiError {

    private Map<String,Object> returnMap;


    public BizApiError(@NonNull HttpStatus status, @NonNull String timestamp, String debugMessage,
                       String warningMessage, String exceptionName, Map<String,Object> map) {
        super(status,timestamp,debugMessage,warningMessage,exceptionName);
        this.returnMap=map;
    }



}
