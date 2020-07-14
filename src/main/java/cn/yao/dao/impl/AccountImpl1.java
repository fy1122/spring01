//package cn.yao.dao.impl;
//
//import cn.yao.dao.AccountDao;
//import org.springframework.beans.factory.support.MethodReplacer;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.lang.reflect.Method;
//
//@Repository("dao1")
//@DependsOn("dao")
//public class  AccountImpl1 implements AccountDao , MethodReplacer {
//    @PostConstruct
//    public void init(){
//        System.out.println("init dao1");
//    }
//    @PreDestroy
//    public void destory() {
//        System.out.println("destory dao1");
//    }
//    public void saveAccount(String s) {
//        System.out.println("保存账户1");
//    }
//
//    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
//        String s = "";
//        saveAccount(s);
//        return null;
//    }
//}
