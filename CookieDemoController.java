package com.example.srilankaairways.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookie")
public class CookieDemoController {

    @GetMapping("/set")
    public String setCookie(HttpServletResponse response){

        ResponseCookie recent =
                ResponseCookie.from(
                                "recentSearch",
                                "CMB-LHR")
                        .httpOnly(true)
                        .secure(true)
                        .build();

        ResponseCookie pref =
                ResponseCookie.from(
                                "preferredAirport",
                                "CMB")
                        .maxAge(86400)
                        .httpOnly(true)
                        .secure(true)
                        .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                recent.toString());

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                pref.toString());

        return "Cookies Set";
    }

    @GetMapping("/read")
    public String read(
            @CookieValue(
                    value="preferredAirport",
                    defaultValue="None")
            String airport){

        return airport;
    }
}
