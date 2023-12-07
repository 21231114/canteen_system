package com.example.myapplication.entity;

public class CommentInfo {
    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public CommentInfo(int comment_id, int send_user_id, int food_id, int receive_user_id, String content) {
        this.comment_id = comment_id;
        this.send_user_id = send_user_id;
        this.food_id = food_id;
        this.receive_user_id = receive_user_id;
        this.content = content;
    }

    public int getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(int send_user_id) {
        this.send_user_id = send_user_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getReceive_user_id() {
        return receive_user_id;
    }

    public void setReceive_user_id(int receive_user_id) {
        this.receive_user_id = receive_user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    int comment_id;
    int send_user_id;
    int food_id;
    int receive_user_id;
    String content;
}
