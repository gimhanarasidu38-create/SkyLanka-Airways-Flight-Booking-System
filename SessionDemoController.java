package com.example.srilankaairways.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionDemoController {

    @PostMapping("/login")
    public String login(HttpSession session){

        session.setAttribute("username","rasidu");
        session.setAttribute("role","CUSTOMER");

        return "Session Created";
    }

    @GetMapping("/profile")
    public Map<String,Object> profile(
            HttpSession session){

        Map<String,Object> map=
                new HashMap<>();

        map.put("username",
                session.getAttribute("username"));

        map.put("role",
                session.getAttribute("role"));

        return map;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "Logged Out";
    }
}
