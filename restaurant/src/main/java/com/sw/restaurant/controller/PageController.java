package com.sw.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @GetMapping(value = "reservation/new")
    public String newReservation(HttpServletRequest httpServletRequest) {

        return "index";
    }

}
