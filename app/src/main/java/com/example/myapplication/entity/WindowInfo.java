package com.example.myapplication.entity;

public class WindowInfo {
    private String window_name;
    private int window_id;
    private String canteen_name;

    public WindowInfo(String window_name, int window_id, String canteen_name) {
        this.window_name = window_name;
        this.window_id = window_id;
        this.canteen_name = canteen_name;
    }

    public String getWindow_name() {
        return window_name;
    }

    public void setWindow_name(String window_name) {
        this.window_name = window_name;
    }

    public int getWindow_id() {
        return window_id;
    }

    public void setWindow_id(int window_id) {
        this.window_id = window_id;
    }

    public String getCanteen_name() {
        return canteen_name;
    }

    public void setCanteen_name(String canteen_name) {
        this.canteen_name = canteen_name;
    }
}
