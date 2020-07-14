package cn.yao.utils;

import java.lang.reflect.Method;

public interface MyInvocationHandler {
    public Object invoke( Method method, Object[] args);
}
