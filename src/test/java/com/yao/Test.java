package com.yao;

import cn.yao.config.SpringConfig;
import cn.yao.dao.AccountDao;
import cn.yao.dao.impl.AccountImpl;
import cn.yao.entity.Account;
import cn.yao.mapper.AccountMapper;
import cn.yao.utils.MyInvocationHandler;
import cn.yao.utils.ProxyUtil;
import cn.yao.utils.uuFactoryBean;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.ProxyGenerator;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test {
    @Autowired
    ApplicationContext context;
    @Autowired
    SqlSessionFactoryBean sqlSessionFactoryBean;

    @org.junit.Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountMapper dao = ac.getBean(AccountMapper.class);
//        sqlSessionFactoryBean.
//        System.out.println(dao.getClass());
        System.out.println(dao.queryById(1));
        System.out.println(Account.class.getName());
        System.out.println(ac.getBean(Account.class));
//        AccountService service1= context.getBean(AccountService.class);
//        AccountService service2= context.getBean(AccountService.class);
//        AccountDao dao= context.getBean(AccountDao.class);
//        AccountDao dao1= context.getBean(AccountDao.class);
//        AccountDao dao2 = (AccountDao) context.getBean(yoyoDao.class);
//        AccountDao dao3= (AccountDao) context.getBean("dao1");
//        dao2.saveAccount();
//        dao3.saveAccount();
//        AccountDao dao=new AccountExtendProxy();
//      final AccountDao target = new AccountImpl();
//        AccountDao dao1 = (AccountDao) ProxyUtil.newInstance(target.getClass().getInterfaces(), new MyInvocationHandler() {
//            public Object invoke( Method method, Object[] args) {
//                try {
//                    System.out.println("fadfasdf");
//                    return method.invoke(target,args);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        });
//        AccountDao dao = (AccountDao) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return "fasdfsa";
//            }
//        });
////       dao.saveAccount("dfas", "fdas");
//        System.out.println(dao1.queryAccount("abc"));
//        dao1.saveAccount("dfas", "fdas");
//        System.out.println(dao1.queryAccount("fds"));
//        AccountDao dao=new AccountExtendProxy();
//        dao.saveAccount();
//        dao.saveAccount();
//        dao1.saveAccount();
////        String applicationName = context.getApplicationName();
//        System.out.println(service1.getClass());
//        System.out.println(service2.getClass());
////        System.out.println(service1.getDao());
////        System.out.println(service2.getDao());
//        service1.saveAccount("hello",1);
//        service2.saveAccount("s",1);
    }

    @org.junit.Test
    public void testProxy() {
        Class<?>[] dao = new Class[]{AccountDao.class};
        byte[] fuyaos = ProxyGenerator.generateProxyClass("fuyao", dao);
        try {
            BufferedOutputStream bp = new BufferedOutputStream(new FileOutputStream("D:\\sjs.class"));
            try {
                bp.write(fuyaos);
                bp.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
