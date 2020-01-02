package com.dhu.bbs.service;

import com.dhu.bbs.dao.MessageDao;
import com.dhu.bbs.dao.UserDao;
import com.dhu.bbs.model.Message;
import com.dhu.bbs.model.MessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;


    @Autowired
    private UserDao userDao;

    public List<Message> getLatestNews(int user_id, int offset, int limit){
        return messageDao.selectByUserIdAndOffset(user_id, offset, limit);
    }

    public List<Message> getLatestNewsBySection(int section, int offset, int limit){
        return messageDao.selectBySectionAndOffset(section, offset, limit);
    }

    public void addMessage(Message message){
        messageDao.messageAdd(message);
    }

    public Message getDetail(int id){
        return messageDao.selectById(id);
    }

    public void likeup(int id, int likeCount)
    {
        messageDao.likeupById(id, likeCount);
    }

    public void commentup(int id){
        messageDao.commentupById(id);
    }

    public void deleteById(int id) { messageDao.deleteById(id);}

    public List<Message> selectMessageByTitle(String name) {
        return messageDao.selectMessageByTitle(name);
    }

    public MessageView messageView(Message message) {
        MessageView messageView = new MessageView();
        messageView.setComment_count(message.getComment_count());
        messageView.setContent(message.getContent());
        messageView.setCreated_date(message.getCreated_date());
        messageView.setId(message.getId());
        messageView.setImage(message.getImage());
        messageView.setLike_count(message.getLike_count());
        messageView.setM_section(message.getM_section());
        messageView.setTitle(message.getTitle());
        messageView.setUser_id(message.getUser_id());
        messageView.setSection(message.getM_section());
        messageView.setUserName(userDao.selectById(message.getUser_id()).getName());

        return messageView;
    }

    public List<Message> getPagingNews(int offset, int limit){
        return messageDao.getPagingNews(offset, limit);
    }

    public int MessageCount(){
        return messageDao.MessageCount();
    }

    public int MessageSectionCount(int m_section){
        return messageDao.MessageSectionCount(m_section);
    }

    public List<Message> getSectionNews(int section, int offset, int limit){
        return messageDao.getSectionNews(section, offset, limit);
    }


    public List<Message> getSearchPagingNews(String search, int offset, int limit){
        return messageDao.getSearchPagingNews(search, offset, limit);
    }

    public int selectMessageByTitleNum(String title){
        return messageDao.selectMessageByTitleNum(title);
    }

}
