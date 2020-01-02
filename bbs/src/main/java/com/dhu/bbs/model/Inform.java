package com.dhu.bbs.model;

import java.util.Date;

public class Inform {
    private int id;
    private int messageId;
    private int informUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getInformUserId() {
        return informUserId;
    }

    public void setInformUserId(int informUserId) {
        this.informUserId = informUserId;
    }
}
