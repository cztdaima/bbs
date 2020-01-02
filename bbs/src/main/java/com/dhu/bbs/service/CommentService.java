package com.dhu.bbs.service;

import com.dhu.bbs.dao.CommentDao;
import com.dhu.bbs.dao.LoginTicketDao;
import com.dhu.bbs.model.Comment;
import com.dhu.bbs.model.LoginTicket;
import com.dhu.bbs.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private LoginTicketDao loginTicketDao;

    public void comment(int message_id, String describe,String ticket) {

        Comment comment = new Comment();
        comment.setTitle_id(message_id);
        comment.setComment(describe);
        Date date = new Date();
        comment.setCreate_date(date);
        LoginTicket loginTicket= loginTicketDao.selectByTicket(ticket);
        comment.setUser_id(loginTicket.getUser_id());
        commentDao.addComment(comment);
    }

    public List<Comment> selectCommentByTitle_id(int id) {
        return commentDao.selectCommentByTitle_id(id);
    }

}
