package com.sw.restaurant.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    String error(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return String.format("<h1>Error occurred: %s </h1>",url);
    }
}
