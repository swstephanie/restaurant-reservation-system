package com.sw.restaurant.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RestaurantAspect{
    private Logger logger =  LoggerFactory.getLogger("FROM AOP");

    @Pointcut("execution(public * com.sw.restaurant.service..*.*(..))")
    public void restaurantPointCut(){

    }
    @Pointcut("execution(public * com.sw.restaurant.service.CustomerService.*(..))")
    public void customerServicePointCut(){

    }

    @Before(value = "customerServicePointCut()")//point cut
    public void doBefore(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        String signatureName = signature.getName();
        logger.info(String.format("Calling method %s", signatureName));
    }
    @After(value = "customerServicePointCut()")//point cut
    public void doAfter(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String signatureName = signature.getName();
        logger.info(String.format("Successfully executed method %s", signatureName));
    }

    @AfterThrowing(value = "restaurantPointCut()", throwing = "Exception")//point cut
    public void handleException(JoinPoint joinPoint, Exception Exception){

        Signature signature = joinPoint.getSignature();
        String signatureName = signature.getName();
        logger.error(String.format("throwing at method %s, error message %s.",signatureName,Exception.getMessage()));
    }

}
