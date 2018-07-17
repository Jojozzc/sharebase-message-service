package com.kele.message_service.msgTest;

import com.kele.message_service.dao.MessageDao;
import com.kele.message_service.model.Message;
import com.kele.message_service.utils.MybatisUtil;
import com.kele.message_service.utils.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MsgDaoTest {

//    @Test
    public void queryTest(){
        SqlSession sqlSession = MybatisUtil.getInstance().getSqlSession();
        MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
        List<Message> messages = messageDao.queryAllMessage();
        for (Message message : messages){
            System.out.println(message.toString());
        }
        sqlSession.close();
    }

    @Test
    public void insertTest(){
        Message message = new Message();
        String id = UUIDUtil.getInstance().getUUID();
        String userId = "zzczzc";
        message.setId(id);
        message.setUserId(userId);
        message.setContent("太热情了");
        SqlSession sqlSession = MybatisUtil.getInstance().getSqlSession();
        MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
        int result = messageDao.insertMessage(message);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(result);
    }
}
