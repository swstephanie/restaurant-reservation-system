package com.sw.restaurant.controller;

import com.sw.restaurant.exception.CustomerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseController {
    //trace<debug<info<warn<error
//    private Logger logger = LoggerFactory.getLogger("Restaurant Logger");
//
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e){
//        logger.error(e.getMessage());
//        return "Something is wrong";
//    }
//    @ExceptionHandler(NullPointerException.class)
//    public String handleNullPointerException(NullPointerException e){
//        logger.error(e.getMessage());
//        return "null pointer";
//    }
//
//    @ExceptionHandler(CustomerException.class)
//    public String handleCustomerException(CustomerException e){
//        logger.error(e.getMessage());
//        return e.getMessage() ;
//    }
}
