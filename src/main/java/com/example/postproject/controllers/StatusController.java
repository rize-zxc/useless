package com.example.postproject.controllers;

import com.example.postproject.models.ServerStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StatusController {

    private final ServerStatus serverStatus = new ServerStatus(true);

    @GetMapping("/")
    public String index(Model model) {
        if (!serverStatus.isAvailable()) {
            model.addAttribute("message", "Сервис временно недоступен. Иди меняй статус.");
            return "error";
        }

        return "index";
    }

    @GetMapping("/status")
    @ResponseBody
    public Map<String, String> checkStatus(@RequestParam(name = "status", required = false) String status) {
        return serverStatus.updateAndGetStatus(status);
    }

    public boolean isServerAvailable() {
        return false;
    }
}