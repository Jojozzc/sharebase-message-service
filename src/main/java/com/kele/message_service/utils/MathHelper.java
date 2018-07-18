package com.kele.message_service.utils;

import java.util.regex.Pattern;

public class MathHelper {
    private static MathHelper instance = new MathHelper();
    private MathHelper(){}

    public static MathHelper getInstance() {
        return instance;
    }
    public boolean isInteger(String str){
        if(str == null || str.length() == 0) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
