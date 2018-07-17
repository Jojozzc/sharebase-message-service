package com.kele.message_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("hello")
public class HelloController {
    @GetMapping("")
    public void sayHello(HttpServletResponse response , HttpServletRequest request){
        System.out.println("hello!");
        try {
            response.getWriter().write("hello message send");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
