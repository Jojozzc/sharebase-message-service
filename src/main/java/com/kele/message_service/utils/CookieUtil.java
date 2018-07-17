package com.kele.message_service.utils;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    private static CookieUtil instance = new CookieUtil();
    private CookieUtil(){}

    public static CookieUtil getInstance() {
        return instance;
    }

    public Map<String, String> getCookieMap(Cookie[] cookies){
        Map<String, String> cookieMap = new HashMap<String, String>();
        if(cookies == null) return cookieMap;
        for(Cookie cookie : cookies){
            cookieMap.put(cookie.getName(), cookie.getValue());
        }
        return cookieMap;
    }
}
