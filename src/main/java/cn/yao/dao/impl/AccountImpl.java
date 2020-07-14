package cn.yao.dao.impl;

import cn.yao.dao.AccountDao;
import cn.yao.ipt.MyImprotSelector;
import cn.yao.service.TestService;
import cn.yao.service.UserService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Repository("dao")
@Primary
@Scope("prototype")
//@DependsOn("accountService")
public class AccountImpl implements AccountDao {
    @PostConstruct
    public void init(){
        System.out.println("init dao");
    }
    @PreDestroy
    public void destory() {
        System.out.println("destory dao");
    }
    public void saveAccount(String s,String s2) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println("保存账户"+this.hashCode());
    }

    public String queryAccount(String s) {
        return s;
    }
}
