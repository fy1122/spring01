package cn.yao.ui;

import cn.yao.config.SpringConfig;
import cn.yao.dao.AccountDao;
import cn.yao.dao.impl.AccountImpl;
import cn.yao.mapper.AccountMapper;
import cn.yao.mapper.UserMapper;
import cn.yao.service.AccountService;
import cn.yao.service.TestService;
import com.sun.org.glassfish.external.amx.AMX;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
//        AccountService  service=new AccountServiceImpl();
        //1获取核心容器
//        ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //2:根据id获取Bean对象
//        AccountImpl bean = context.getBean(AccountImpl.class);
        TestService bean = (TestService) context.getBean("getTestService");
        System.out.println(bean.getClass());
//        bean.test();
//        AccountMapper mapper = (AccountMapper) context.getBean("AccountMapper");
//        UserMapper mapper1 = (UserMapper) context.getBean("UserMapper");
//        mapper.queryById(1);
//        mapper1.queryName("fad");
//        AccountService service1= context.getBean(AccountService.class);
//        AccountService service2= context.getBean(AccountService.class);
//        String applicationName = context.getApplicationName();
//        System.out.println(service1.getClass());
//        System.out.println(service2.getClass());
//        System.out.println(service1.getDao());
////        System.out.println(service2.getDao());
//        service1.saveAccount();
//        service2.saveAccount();


    }
}
