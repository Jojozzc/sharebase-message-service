package com.kele.message_service.config;

import com.kele.message_service.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean("user")
    public User getUser(){
        User user = new User();
        return user;
    }
}
