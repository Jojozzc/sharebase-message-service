package com.kele.message_service.msgTest;

import com.kele.service.ISignInConfirm;
import org.junit.Test;

import java.rmi.Naming;

public class RMITest {
    @Test
    public void confirmTest(){
        try {
            ISignInConfirm confirm = (ISignInConfirm)Naming.lookup("rmi://127.0.0.1:9090/signInConfirm");
            String token = "uuutoken";
            String userId = confirm.confirmAndGetUserId(token);
            System.out.println("token:" + token);
            System.out.println("userId:" + userId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
