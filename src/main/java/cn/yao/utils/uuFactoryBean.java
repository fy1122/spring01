package cn.yao.utils;

import cn.yao.entity.Account;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class uuFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Account();
    }

    @Override
    public Class<?> getObjectType() {
        return Account.class;
    }
}
