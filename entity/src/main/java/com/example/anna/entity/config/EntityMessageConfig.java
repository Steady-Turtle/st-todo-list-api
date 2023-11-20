package com.example.anna.entity.config;

import com.example.anna.entity._common.EntityMessageUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * 다국어 설정
 */
@Configuration
public class EntityMessageConfig {

    @Bean
    public LocaleResolver localeResolverEntity() {
        var localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);  //언어&국가정보가 없는 경우 한국으로 인식
        return localeResolver;
    }


    @Bean
    public MessageSource messageSourceEntity() {
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/entity-messages/message");
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setCacheSeconds(180); // 리로딩 간격
        Locale.setDefault(Locale.KOREA); // 제공하지 않는 언어로 요청이 들어왔을 때 MessageSource에서 사용할 기본 언어정보.

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessorEntity() {
        return new MessageSourceAccessor(this.messageSourceEntity());
    }

    @Bean
    public void messageUtilsEntity() {
        EntityMessageUtil.setMessageSourceAccessor(this.messageSourceAccessorEntity());
    }
}
