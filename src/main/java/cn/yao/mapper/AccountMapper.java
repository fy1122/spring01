package cn.yao.mapper;

import cn.yao.entity.Account;
import org.apache.ibatis.annotations.Select;

public interface AccountMapper {
    @Select("select * from  account where id=#{id}")
    public Account queryById(Integer id);


}
