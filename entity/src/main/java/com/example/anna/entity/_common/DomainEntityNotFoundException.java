package com.example.anna.entity._common;

import lombok.Getter;

@Getter
public class DomainEntityNotFoundException extends RuntimeException {
    private String warningMessage;
    private String entityClazz;

    public DomainEntityNotFoundException(Class<?> entityClazz, String code) {
        this.entityClazz = entityClazz.getSimpleName();
        this.warningMessage = EntityMessageUtil.getMessage(code);
    }
}
