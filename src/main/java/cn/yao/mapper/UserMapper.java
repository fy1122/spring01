package cn.yao.mapper;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where id = #{id}")
    String queryName(String id);
}
