package com.kele.message_service.msgTest;

import com.kele.message_service.config.MessageConfig;
import com.kele.message_service.model.Message;
import com.kele.message_service.utils.SpringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MessageTest {
    private static AnnotationConfigApplicationContext applicationContext;
    @Test
    public void iocTest(){
        applicationContext = new AnnotationConfigApplicationContext(MessageConfig.class);
        Message msg = (Message) applicationContext.getBean("message");
        Message msg2 = (Message) applicationContext.getBean("message");
        System.out.println(msg.toString());
        System.out.println(msg2);
    }
}
