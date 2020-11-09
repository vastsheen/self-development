package com.xh.demo.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TestAspect {

    @Pointcut("execution(* com.xh.demo.aop.ProxyTarget.say())")
    public void test() {

    }

    @Around("test()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            Object proceed = joinPoint.proceed();
            System.out.println("proxy: world");
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
