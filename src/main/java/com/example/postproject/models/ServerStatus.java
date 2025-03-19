package com.example.postproject.models;

public class ServerStatus {
    private static ServerStatus instance;
    private boolean available;


    private ServerStatus(boolean available) {
        this.available = available;
    }


    public static ServerStatus getInstance() {
        if (instance == null) {
            instance = new ServerStatus(true);
        }
        return instance;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}