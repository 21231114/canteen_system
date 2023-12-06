package com.example.myapplication.entity;

public class HistoryInfo {
    int history_id;
    int user_id;

    public HistoryInfo(int history_id, int user_id, int food_id, String food_time) {
        this.history_id = history_id;
        this.user_id = user_id;
        this.food_id = food_id;
        this.food_time = food_time;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
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

    int food_id;
    String food_time;
}
