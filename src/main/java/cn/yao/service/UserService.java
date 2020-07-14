package cn.yao.service;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


public class UserService extends TestService{
    public UserService() {
        System.out.println("userService");
    }
}
