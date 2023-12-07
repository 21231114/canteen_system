package com.example.myapplication.User;

public class Sort implements Comparable<Sort> {
    private int cnt;
    private int food_id;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public Sort(int cnt, int food_id) {
        this.cnt = cnt;
        this.food_id = food_id;
    }

    @Override
    public int compareTo(Sort sort) {
        if (food_id == sort.food_id) {
            return 0;//代表是同一个对象
        }
        if (cnt > sort.cnt) {
            return -1;//浏览与点单多的在前
        } else {
            return 1;
        }
    }
}
