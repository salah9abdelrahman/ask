package com.salah.ask.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Pointcut("execution(public * com.salah.ask.security.*.*(..))")
    private void securityPackagePointCut() {
    }

    @Pointcut("execution(public * com.salah.ask.security.jwt.*.*(..))")
    private void jwtPackagePointCut() {
    }

    @Pointcut("execution(public * com.salah.ask.controller.*.*(..))")
    private void controllerPackagePointCut() {
    }

    @Pointcut("execution(public * com.salah.ask.controller.*.*(..))")
    private void authControllerPackagePointCut() {
    }

    @Pointcut("execution(public * com.salah.ask.repository.*.*(..))")
    private void repositoryPackagePointCut() {
    }

    @Pointcut("execution(public * com.salah.ask.service.*.*(..))")
    private void servicePackagePointCut() {
    }

    @Pointcut("securityPackagePointCut() || jwtPackagePointCut()" +
            " || controllerPackagePointCut() || authControllerPackagePointCut() || servicePackagePointCut() " +
            "||  repositoryPackagePointCut() ")
    private void applicationPointCuts() {
    }

    @Around("applicationPointCuts()")
    private Object logging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Entering method:" + proceedingJoinPoint.getSignature());
        StringBuilder args = new StringBuilder("with parameters:");
        Stream.of(proceedingJoinPoint.getArgs()).forEach(args::append);
        log.info(args.toString());

        final Object retVal = proceedingJoinPoint.proceed();

        log.info("result:" + retVal);

        return retVal;

    }


}
