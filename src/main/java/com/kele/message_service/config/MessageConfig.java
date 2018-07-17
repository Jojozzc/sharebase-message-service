package com.kele.message_service.config;

import com.kele.message_service.model.Message;
import com.kele.message_service.utils.UUIDUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean("message")
    public Message message(){
        ApplicationContext applicationContext;
        Message msg = new Message();
        msg.setId(UUIDUtil.getInstance().getUUID());
        return msg;
    }
}
