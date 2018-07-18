package com.kele.message_service.dao;

import com.kele.message_service.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {
    public int insertMessage(Message message);
    public int updateMessage(Message message);
    public int deleteMessage(Message message);
    public Message queryMessageById(String messageId);
    public List<Message> queryAllMessage();
    public List<Message> queryAllMessageByUserId(String userId);
    public List<Message> queryAllOrderByTimeWithRange(@Param("0")int start, @Param("1") int offset);
}
