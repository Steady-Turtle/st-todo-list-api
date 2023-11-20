package com.example.anna.api._config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.PostConstruct;

/**
 * 다국어 설정
 */
@Configuration
public class UserMessageConfig {

    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    @PostConstruct
    public void addMessageBaseName() {
        messageSource.addBasenames("classpath:/user-messages/message");
    }

}
