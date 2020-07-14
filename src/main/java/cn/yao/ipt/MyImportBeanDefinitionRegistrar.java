package cn.yao.ipt;

import cn.yao.anno.FuYao;
import cn.yao.config.SpringConfig;
import cn.yao.utils.MyFactoryBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar,InvocationHandler {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        String [] s= (String[]) importingClassMetadata.getAnnotationAttributes(FuYao.class.getName()).get("value");
        String basePath = this.getClass().getResource("/").getPath();
//        System.out.println(s1);
        for (int i = 0; i < s.length; i++) {
            s[i]=s[i].replaceAll("\\.", "/");
            File file = new File(basePath + "/" + s[i]);
            List<Class<?>> clazzs = getFile(file, s[i]);
            System.out.println(clazzs.size());
            clazzs.forEach(clazz->{
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Proxy.getProxyClass(this.getClass().getClassLoader(),new Class[]{clazz}));
//                builder.addConstructorArgValue(this);
                GenericBeanDefinition genericBeanDefinition= (GenericBeanDefinition) builder.getBeanDefinition();
                genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazz);
//                System.out.println(clazz.getInterfaces().length);
                genericBeanDefinition.setBeanClass(MyFactoryBean.class);
                registry.registerBeanDefinition(clazz.getSimpleName(),genericBeanDefinition);
            });
        }



    }

    List<Class<?>> getFile(File file,String basePak) {
        List<Class<?>> clazzs=new ArrayList<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    getFile(file1,basePak);
                } else {
                    try {
                       String className=basePak+"/"+file1.getName().toString().substring(0,file1.getName().toString().lastIndexOf("."));
                        Class<?> clazz = Class.forName(className.replaceAll("\\/" ,"."));
                        clazzs.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return clazzs;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Select[] annotationsByType = method.getAnnotationsByType(Select.class);
        System.out.println("proxy");
        return null;
    }
}
