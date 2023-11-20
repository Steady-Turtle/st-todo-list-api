package com.example.anna.api._config;

import com.example.anna.entity._common.EnumMapper;
import com.example.anna.entity.user.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EnumMapperConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();

        enumMapper.put("RoleType", User.Role.class);

        return enumMapper;
    }
}
