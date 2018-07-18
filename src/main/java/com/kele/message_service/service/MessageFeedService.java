package com.kele.message_service.service;

import com.google.gson.Gson;
import com.kele.StatusCodeConfig;
import com.kele.message_service.controller.MessageController;
import com.kele.message_service.dao.MessageDao;
import com.kele.message_service.model.Message;
import com.kele.message_service.utils.MathHelper;
import com.kele.message_service.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MessageFeedService {
    private static Gson gson = new Gson();
    public static void msgFeed(HttpServletRequest request, HttpServletResponse response,
                               MessageController.MsgResponseBody responseBody){
        response.setCharacterEncoding("utf-8");
        String rangeA = request.getParameter("from");
        String rangeB = request.getParameter("to");
        responseBody.setCode(StatusCodeConfig.SERVER_ERROR);


        responseBody.setMessage("Unknown Error");
        if(rangeA == null || rangeB == null || rangeA.length() == 0 || rangeB.length() == 0) {
            responseBody.setCode(StatusCodeConfig.PARAM_LACK);
            responseBody.setMessage("缺少必要参数");
        }
        else{
            if(MathHelper.getInstance().isInteger(rangeA) && MathHelper.getInstance().isInteger(rangeB)){
                // xx.xx.xx.xx:8080/msg/feed
                // get
                // para:
                //      from 起始
                //      to   终止
                try {
                    int from = Integer.valueOf(rangeA);
                    int to = Integer.valueOf(rangeB);
                    if(from <= 0 || from > to || to == Integer.MAX_VALUE) throw new Exception("范围有误");
                    List<Message> result = queryHot(from , to);
                    responseBody.setCode(StatusCodeConfig.SUCCESS);
                    responseBody.setMessageList(result);
                    responseBody.setMessage("查询成功");
                    System.out.println("查询成功");
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("查询失败");
                    responseBody.setCode(StatusCodeConfig.UNKNOWN_ERROR);
                }

            }
            else {
                responseBody.setCode(StatusCodeConfig.WRONG_PARAM);
                responseBody.setMessage("范围参数有误");
                System.out.println("范围参数有误");
            }
        }

        try {
            String json = gson.toJson(responseBody);

//            String json = new String(gson.toJson(responseBody).getBytes("ISO8859-1"), "utf-8");
            System.out.println(json);
            response.getWriter().write(json);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Message> queryByUserId(String id){
        SqlSession sqlSession = MybatisUtil.getInstance().getSqlSession();
        MessageDao messageDao = (MessageDao) sqlSession.getMapper(MessageDao.class);
        List<Message> result = messageDao.queryAllMessageByUserId(id);
        return result;
    }

    private static List<Message> queryHot(int from, int to){
        // from和to在此转换成数据库范围
        to++;
        from--;
        SqlSession sqlSession = MybatisUtil.getInstance().getSqlSession();
        MessageDao messageDao = (MessageDao) sqlSession.getMapper(MessageDao.class);
        List<Message> result = messageDao.queryAllOrderByTimeWithRange(from, to);
        return result;
    }
}
