package com.example.anna.entity._common;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * 다국어 메세지
 */
public class EntityMessageUtil {
    static MessageSourceAccessor messageSourceAccessorEntity;

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        EntityMessageUtil.messageSourceAccessorEntity = messageSourceAccessor;
    }

    public static String getMessage(String key) {
        return messageSourceAccessorEntity.getMessage(key);
    }

}
