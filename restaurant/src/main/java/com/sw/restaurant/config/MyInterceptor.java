package com.sw.restaurant.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        //do not allow post
//        if("POST".equalsIgnoreCase(method)){
//            response.setStatus(403);
//            response.sendError(403,"POST IS NOT SUPPORTED.");
//            return false;
//        }
        StringBuffer URL = request.getRequestURL();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }
        return true;
    }
}
