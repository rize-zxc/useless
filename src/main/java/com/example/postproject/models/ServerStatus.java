package com.example.postproject.models;

import java.util.HashMap;
import java.util.Map;

public class ServerStatus {
    private boolean available;

    public ServerStatus(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Map<String, String> updateAndGetStatus(String status) {
        if (status != null) {
            if ("available".equalsIgnoreCase(status)) {
                this.available = true;
            } else if ("unavailable".equalsIgnoreCase(status)) {
                this.available = false;
            }
        }

        Map<String, String> response = new HashMap<>();
        if (this.available) {
            response.put("status", "available");
            response.put("message", "Сервис работает в штатном режиме.");
        } else {
            response.put("status", "unavailable");
            response.put("message", "Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }

        return response;
    }
}