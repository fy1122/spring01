package cn.yao.config;

import cn.yao.anno.FuYao;
import cn.yao.dao.AccountDao;
import cn.yao.dao.impl.AccountImpl;
import cn.yao.ipt.MyImprotSelector;
import cn.yao.service.TestService;
import cn.yao.service.UserService;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
//
@Configuration
@ComponentScan(basePackages = "cn.yao",excludeFilters ={@ComponentScan.Filter(type = FilterType.REGEX,pattern="cn.yao.jdbc.*")})
@ImportResource(locations = "classpath:bean.xml")
@Import({JdbcConfig.class})
@MapperScan("cn.yao.mapper")
//@FuYao("cn.yao.mapper")
//@Import(MyImprotSelector.class)
@EnableAspectJAutoProxy()
public class SpringConfig {
    //    @Bean()
//    @Qualifier("dao")
//    public AccountDao getDao(){
//        return new AccountImpl();
//    }
//    @Bean()
//    public AccountDao getDao1(){
//        return new AccountImpl();
//    }


}
