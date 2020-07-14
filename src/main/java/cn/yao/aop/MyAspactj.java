package cn.yao.aop;

import cn.yao.dao.AccountDao;
import cn.yao.dao.impl.AccountImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspactj {
    @DeclareParents(value = "cn.yao.dao.impl.*",defaultImpl = AccountImpl.class)
    public static AccountDao accountDao;
    @Pointcut("execution(* cn.yao.*.*.*(..))")
    public void pointCut() {
    }

    @Pointcut("within(cn.yao.config..*)")
    public void winthin() {
    }

    @Pointcut("args(java.lang.String,..)")
    public void arges() {

    }

    @Pointcut("@annotation(cn.yao.anno.MyAnnotation)")
    public void poinCutAnno() {

    }

    @Pointcut("this(cn.yao.service.AccountService)")
    public void poinCutThis() {

    }

//    @Around("pointCut()&&!winthin() ")
    public void around(ProceedingJoinPoint point) {
        try {
            System.out.println("前置");
            System.out.println(this.hashCode());
            point.proceed();
            System.out.println("后置");
        } catch (Throwable throwable) {
            System.out.println("异常");
            throwable.printStackTrace();
        }finally {
            System.out.println("最终");
        }
    }
    @After("poinCutThis()")
    public void afterService() {
        System.out.println("after advice");
    }
}
