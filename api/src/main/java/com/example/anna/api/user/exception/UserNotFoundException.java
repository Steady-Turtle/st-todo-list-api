package com.example.anna.api.user.exception;


import com.example.anna.common._common.util.MessageUtil;
import com.example.anna.common._support.exception.CustomException;


public class UserNotFoundException extends CustomException {


    public UserNotFoundException() {
        super(MessageUtil.getMessage("USER_NOT_FOUND"));
    }

}
