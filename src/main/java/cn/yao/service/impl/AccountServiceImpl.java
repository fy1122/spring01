//package cn.yao.service.impl;
//
//import cn.yao.anno.MyAnnotation;
//import cn.yao.dao.AccountDao;
//import cn.yao.dao.impl.AccountImpl;
//import cn.yao.service.AccountService;
//import com.sun.org.apache.xml.internal.security.Init;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanNameAware;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Lookup;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Required;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanNameGenerator;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Generated;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service("accountService")
////@Scope("prototype")
//public class AccountServiceImpl implements AccountService {
//    //    public void afterPropertiesSet() throws Exception {
////        System.out.println("init");
////    }
////    //    @PostConstruct
////
////    public void destroy() throws Exception {
////        System.out.println("destory");
////    }
//    @PostConstruct
//    public void init() {
//        System.out.println("init");
//    }
//
//    //    @PreDestroy
//    public void destroy() {
//        System.out.println("destory");
//    }
//
//    private ApplicationContext applicationContext;
//
//
//    //    private ApplicationContext applicationContext;
//    @Autowired
//    @Qualifier("dao")
//    private AccountDao dao;
////    @Lookup("dao")
////    public abstract AccountDao createDao();
////    public AccountDao getDao() {
////        return dao;
////    }
////    public void setDao(AccountDao dao) {
////        this.dao = dao;
////    }
//
//    @MyAnnotation
//    public void saveAccount(String s,int i) {
//
//        System.out.println(s);
//            dao.saveAccount(s);
//
//    }
//
////    protected AccountDao createCommand() {
////        // notice the Spring API dependency!
////        return this.applicationContext.getBean(AccountDao.class);
////    }
////
////    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
////        this.applicationContext=applicationContext;
////    }
//}