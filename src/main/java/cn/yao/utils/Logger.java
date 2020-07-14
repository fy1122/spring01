package cn.yao.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect("perthis(cn.yao.aop.MyAspactj.poinCutThis())")
@Component
@Scope("prototype")
public class Logger {
    @Pointcut("execution(* cn.yao.service.*.*(..))")
    public void poinCut() {
    }

    @Before("poinCut()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("前置通知开始记录日志");
        System.out.println(joinPoint.getArgs());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getThis());
    }

    @After("poinCut()")
    public void afterLog() {
        System.out.println("后置通知开始记录日志");
    }

    @AfterReturning("poinCut()")
    public void afterReturningLog() {
        System.out.println("最终通知开始记录日志");
    }

    @AfterThrowing("poinCut()")
    public void afterThrowingLog() {
        System.out.println("异常通知开始记录日志");
    }

    @Around("poinCut()")
    public void aroundLog(ProceedingJoinPoint point) {
        System.out.println("around的前置通知");
        System.out.println(this.hashCode());
        Object[] args = point.getArgs();
        if (null != args && args.length > 0) {
            args[0] += "world";
        }
        try {
            point.proceed(args);
            System.out.println("around的后置通知");
        } catch (Throwable throwable) {
            System.out.println("around的异常通知");
            throwable.printStackTrace();
        }finally {
            System.out.println("around的最终通知");
        }
    }

}
