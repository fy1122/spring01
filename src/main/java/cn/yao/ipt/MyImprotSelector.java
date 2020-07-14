package cn.yao.ipt;

import cn.yao.dao.impl.AccountImpl;
import cn.yao.service.TestService;
import cn.yao.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
@Configuration
public class MyImprotSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{AccountImpl.class.getName()};
    }


}
