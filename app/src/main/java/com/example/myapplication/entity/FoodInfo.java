package com.example.myapplication.entity;

public class FoodInfo {
    private int food_id;
    private String food_name;
    private int food_type;

    public int getFood_type() {
        return food_type;
    }

    public void setFood_type(int food_type) {
        this.food_type = food_type;
    }

    public FoodInfo(int food_id, String food_name, int foodType, String canteen_name, String window_name) {
        this.food_id = food_id;
        this.food_name = food_name;
        food_type = foodType;
        this.canteen_name = canteen_name;
        this.window_name = window_name;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCanteen_name() {
        return canteen_name;
    }

    public void setCanteen_name(String canteen_name) {
        this.canteen_name = canteen_name;
    }

    public String getWindow_name() {
        return window_name;
    }

    public void setWindow_name(String window_name) {
        this.window_name = window_name;
    }

    private String canteen_name;
    private String window_name;

}
