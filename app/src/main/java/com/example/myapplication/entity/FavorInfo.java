package com.example.myapplication.entity;

public class FavorInfo {
    int favor_id;
    int user_id;

    public int getFavor_id() {
        return favor_id;
    }

    public FavorInfo(int favor_id, int user_id, int food_id, int type) {
        this.favor_id = favor_id;
        this.user_id = user_id;
        this.food_id = food_id;
        this.type = type;
    }

    public void setFavor_id(int favor_id) {
        this.favor_id = favor_id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int food_id;
    int type;


}
