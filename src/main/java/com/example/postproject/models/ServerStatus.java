package com.example.postproject.models;

import org.springframework.stereotype.Component;

@Component //(9(
public class ServerStatus {
    private boolean available;

    public ServerStatus() {
        this.available = true; 
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
