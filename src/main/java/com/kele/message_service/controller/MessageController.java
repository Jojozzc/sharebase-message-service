package com.kele.message_service.controller;

import com.google.gson.Gson;
import com.kele.StatusCodeConfig;
import com.kele.message_service.body.BaseResponseBody;
import com.kele.message_service.service.SendMessageService;
import com.kele.message_service.utils.CookieUtil;
import com.kele.message_service.utils.UUIDUtil;
import com.kele.service.ISignInConfirm;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.Naming;
import java.util.Map;

// xx.xx.xx.xx/msg/send
// para:
// userId
// token  (required)
// content



@Controller
@RequestMapping("msg")
public class MessageController {
    private static AnnotationConfigApplicationContext applicationContext;
    private static Gson gson = new Gson();
    @PostMapping("/send")
    public void sendMsg(HttpServletRequest request, HttpServletResponse response){
        MsgResponseBody responseBody = new MsgResponseBody();
        System.out.println();
        try{

            Cookie[] cookies = request.getCookies();
            Map<String, String> cookieMap = CookieUtil.getInstance().getCookieMap(cookies);
            ISignInConfirm userConfirm = (ISignInConfirm)Naming.lookup("rmi://127.0.0.1:9090/signInConfirm");

            if(cookieMap.containsKey("token")){
//            if(true){
                String userId = userConfirm.confirmAndGetUserId(cookieMap.get("token"));
//                System.out.println("测试阶段" + "user id:" + userId);
//                userId = "zzczzc";
                if(userId == null || userId.equals("")){

                    responseBody.setCode(StatusCodeConfig.NO_SIGNIN);
                    responseBody.setMessage("未登录");
                    System.out.println("验证未通过");

                }
                else{
                    System.out.println("验证通过");
                    String content = request.getParameter("content");
                    int type = 0;
                    if(content == null) {
                        responseBody.setCode(StatusCodeConfig.PARAM_LACK);
                        responseBody.setMessage("内容不能为空");
                    }
                    else {
                        if(content.length() > 120){
                            responseBody.setCode(StatusCodeConfig.MSG_LEN_EXCEED);
                            responseBody.setMessage("内容过长(不能超过120字符)");
                        }
                        else {
                            try {
                                SendMessageService.send(userId, content, type);
                                responseBody.setCode(StatusCodeConfig.SUCCESS);
                            }catch (Exception e){
                                responseBody.setCode(StatusCodeConfig.SERVER_ERROR);
                            }
                        }
                    }

                }

//                request.getRequestDispatcher("/main.html").forward(request, response);
//                response.sendRedirect("main.html");
            }
            else {
                responseBody.setCode(StatusCodeConfig.NO_SIGNIN);
                responseBody.setMessage("未登录");
//                request.getRequestDispatcher("/index.html").forward(request, response);
                System.out.println("验证未通过");
            }
        }catch (Exception e){
            e.printStackTrace();
            responseBody.setCode(StatusCodeConfig.SERVER_ERROR);
            responseBody.setMessage("未知错误");
        }
        try {
            response.getWriter().write(gson.toJson(responseBody));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    class MsgResponseBody extends BaseResponseBody{

    }
}
