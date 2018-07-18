package com.kele.message_service.msgTest;

import com.kele.message_service.utils.MathHelper;
import org.junit.Test;

import java.util.Scanner;

public class MathHelperTest {
    @Test
    public void integerTest(){
        String str = "";
        System.out.println(MathHelper.getInstance().isInteger(str));
    }
}
