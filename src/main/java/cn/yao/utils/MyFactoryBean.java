package cn.yao.utils;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFactoryBean implements FactoryBean, InvocationHandler {
    private  Class[]classes;

    public MyFactoryBean(Class ... classes) {
        this.classes = classes;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),classes,this::invoke);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy factoryBean");
        Select[] annotationsByType = method.getAnnotationsByType(Select.class);
        for (Select select : annotationsByType) {
            System.out.println(select.value()[0]);
        }
        return null;
    }
}
