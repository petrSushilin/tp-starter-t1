package ru.t1.school.open.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final LoggingAspectConfiguration configurations;

    public LoggingAspect(LoggingAspectConfiguration configurations) {
        this.configurations = configurations;
    }

    @Pointcut("@annotation(ru.t1.school.open.logger.annotation.Logging)")
    public void loggingMethods() {
    }

    @Before("loggingMethods()")
    public void logBefore(JoinPoint joinPoint) {
        LoggingLevel loggerLevel = configurations.getLoggerLevel();
        if (loggerLevel == LoggingLevel.SHORT) {
            logger.info("Executing method {}", joinPoint.getSignature().toShortString());
        } else {
            logger.info("Location: {}. Executing method {}. With args: {}", joinPoint.getSourceLocation(), joinPoint.getSignature().toLongString(), joinPoint.getArgs());
        }
    }


    // Выводить короткое либо длинное сообщение в зависимости от установленного в configurations значения
    @After("loggingMethods()")
    public void logAfter(JoinPoint joinPoint) {
        LoggingLevel loggerLevel = configurations.getLoggerLevel();
        if (loggerLevel == LoggingLevel.SHORT) {
            logger.info("Method {} executed", joinPoint.getSignature().toShortString());
        } else {
            logger.info("Location: {}. Method {} finally executed. With args: {}", joinPoint.getSourceLocation(), joinPoint.getSignature().toLongString(), joinPoint.getArgs());
        }
    }

    @AfterReturning(pointcut = "loggingMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} executed successfully. Returned: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "loggingMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method {} threw an exception: {}", joinPoint.getSignature(), exception.getMessage());
    }

    @Around("loggingMethods()")
    public Object loggingTime(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        logger.debug("Method executed in {} ms", endTime - startTime);
        return result;
    }
}
