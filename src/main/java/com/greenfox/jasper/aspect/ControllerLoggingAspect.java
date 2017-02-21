package com.greenfox.jasper.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by almasics on 2017.02.20..
 */

@Component
@Aspect
public class ControllerLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logControllerCall(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String clazz = signature.getDeclaringTypeName();

        logger.debug("Entering method [{}] of controller [{}], arguments: [{}]", methodName, clazz, joinPoint.getArgs());

        Object result = joinPoint.proceed();

        logger.debug("Exiting method [{}] of controller [{}], return value: [{}]", methodName, clazz, result);

        return result;
    }

}
