package com.YELL.pocketMedic.Entities;

public class Comment {

    public long accountId;
    private String comment;

    public Comment() {
    }

    public Comment(long accountId, String comment) {
        this.accountId = accountId;
        this.comment = comment;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}

