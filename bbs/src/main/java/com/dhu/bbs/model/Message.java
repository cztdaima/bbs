package com.dhu.bbs.model;

import java.util.Date;

public class Message {
    private int id;
    private String title;
    private String image;
    private int like_count;
    private int comment_count;
    private Date created_date;
    private int user_id;
    private  int m_section;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getM_section() {
        return m_section;
    }

    public void setM_section(int m_section) {
        this.m_section = m_section;
    }
    public String getSection(){
        if(m_section==1)
            return "娱乐";
        else if(m_section==2)
            return "新闻";
        else
            return "知识";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
