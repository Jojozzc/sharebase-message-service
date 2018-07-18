package com.kele.message_service.auto;

import com.kele.message_service.dao.MessageDao;
import com.kele.message_service.model.Message;
import com.kele.message_service.utils.MybatisUtil;
import com.kele.message_service.utils.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class SendMessage {
    @Test
    public void autoSend(){
        int MAX = 10;
        String userId = "zzczzc";
        SqlSession sqlSession = MybatisUtil.getInstance().getSqlSession();
        MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
        Message message = new Message();
        for(int i = 0; i < MAX; i++){
            message.setId(UUIDUtil.getInstance().getUUID());
            message.setUserId(userId);
            message.setContent("捡到" + i + "块钱");
            message.setType(0);
            messageDao.insertMessage(message);

        }
        sqlSession.commit();
        sqlSession.close();
    }
}
