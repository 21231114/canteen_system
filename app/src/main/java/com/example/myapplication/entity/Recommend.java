package com.example.myapplication.entity;

public class Recommend {
    private  int recommend_id;
    private int user_id;

    public int getRecommend_id() {

        return recommend_id;
    }

    public void setRecommend_id(int recommend_id) {
        this.recommend_id =  recommend_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_time() {
        return food_time;
    }

    public void setFood_time(String food_time) {
        this.food_time = food_time;
    }

    public Recommend(int  recommend_id, int user_id, int food_id, String food_time) {
        this.recommend_id =  recommend_id;
        this.user_id = user_id;
        this.food_id = food_id;
        this.food_time = food_time;
    }

    private int food_id;
    String food_time;
}
