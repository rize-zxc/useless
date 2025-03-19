package com.example.postproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StatusController {

    private boolean serverStatus = true;

    @GetMapping("/")
    public String products(Model model) {
        if (!isServerAvailable()) {
            model.addAttribute("message", "Сервис временно недоступен. Иди меняй статус.");
            return "error";
        }

        return "index";
    }

    @GetMapping("/status")
    @ResponseBody
    public Map<String, String> checkStatus(@RequestParam(name = "status", required = false) String status) {
        return updateAndGetStatus(status);
    }

    public boolean isServerAvailable() {
        return serverStatus;
    }

    private Map<String, String> updateAndGetStatus(String status) {
        if (status != null) {
            if ("available".equalsIgnoreCase(status)) {
                serverStatus = true;
            } else if ("unavailable".equalsIgnoreCase(status)) {
                serverStatus = false;
            }
        }

        Map<String, String> response = new HashMap<>();
        if (serverStatus) {
            response.put("status", "available");
            response.put("message", "Сервис работает в штатном режиме.");
        } else {
            response.put("status", "unavailable");
            response.put("message", "Сервис временно недоступен. Пожалуйста, попробуйте позже.");
        }

        return response;
    }
}