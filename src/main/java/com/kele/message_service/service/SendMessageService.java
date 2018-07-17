package com.kele.message_service.service;

import com.kele.message_service.dao.MessageDao;
import com.kele.message_service.model.Message;
import com.kele.message_service.utils.MybatisUtil;
import com.kele.message_service.utils.UUIDUtil;
import org.apache.ibatis.session.SqlSession;

public class SendMessageService {
    private static void send(Message message)throws Exception{
        SqlSession sqlSession = MybatisUtil.getInstance().getSqlSession();
        MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
        int result = messageDao.insertMessage(message);
        sqlSession.commit();
        sqlSession.close();
    }

    public static void send(String userId, String content, int type)throws Exception{
        Message message = new Message();
        message.setUserId(userId);
        message.setId(UUIDUtil.getInstance().getUUID());
        message.setContent(content);
        message.setType(type);
        send(message);
    }
}
