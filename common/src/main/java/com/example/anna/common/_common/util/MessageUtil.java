package com.example.anna.common._common.util;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * 다국어 메세지
 */
public class MessageUtil {
    static MessageSourceAccessor messageSourceAccessor;

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessageUtil.messageSourceAccessor = messageSourceAccessor;
    }

    public static MessageSourceAccessor getMessageSourceAccessor() {
        return messageSourceAccessor;
    }

    public static String getMessage(String key) {
        return messageSourceAccessor.getMessage(key);
    }

    public static String getMessage(String key, Object[] objs) {
        return messageSourceAccessor.getMessage(key, objs);
    }
}
