package com.sw.restaurant.config;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class RestaurantFilter implements Filter {

    //MY FILETER
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        StringBuffer URL = httpServletRequest.getRequestURL();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while(headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
