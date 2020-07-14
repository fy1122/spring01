package cn.yao.utils;

import cn.yao.dao.AccountDao;
import org.springframework.cglib.beans.BulkBean;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProxyUtil {

    public static Object newInstance(Class<?> []clazz,MyInvocationHandler handler) {
        Method[] methods = null;
//        List<Method> methods=new ArrayList<Method>();
        StringBuilder builder = new StringBuilder("");
        builder.append("package cn.yao.proxy;\n");
        for (int i = 0; i < clazz.length; i++) {
            builder.append("import " + clazz[i].getName() + ";\n");
//            Collections.addAll(methods,clazz[i].getDeclaredMethods());
//            builder.append("import " + clazz.getInterfaces()[0].getName() + ";\n");
        }
        builder.append("import " + Object.class.getName() + ";\n");
        builder.append("import " + Method.class.getName() + ";\n");
        builder.append("import " + Exception.class.getName() + ";\n");
        builder.append("import " + MyInvocationHandler.class.getName() + ";\n");
        builder.append("public class $Proxy implements ");
        for (int i = 0; i < clazz.length; i++) {
            if (i==clazz.length-1)
            builder.append(clazz[i].getSimpleName());
            else builder.append(clazz[i].getSimpleName() + ",");
        }
        builder.append(" {\n");
        builder.append("\tprivate MyInvocationHandler handler;\n");
        builder.append("\tpublic $Proxy(MyInvocationHandler handler){\n");
        builder.append("\t\tthis.handler=handler;\n\t}\n");
        for (int i = 0; i < clazz.length; i++) {
            methods =clazz[i].getDeclaredMethods();
            for (Method method : methods) {
                Class<?>[] parameterTypes = method.getParameterTypes();

                String s = "";
                for (int i1 = 0; i1 < parameterTypes.length; i1++) {
                    if (i1==parameterTypes.length-1)
                        s+= parameterTypes[i1].getSimpleName()+".class";
                 else   s+= parameterTypes[i1].getSimpleName()+".class,";
                }
                builder.append("\tpublic " + method.getReturnType().getSimpleName() + " " + method.getName() + " ("
                );
                String str="Object[]args=new Object[]{";
                for (int j = 0; j < parameterTypes.length; j++) {
                    if (j == parameterTypes.length-1) {
                        str += "j"+j+"};\n";
                        builder.append(parameterTypes[j].getSimpleName() + " j" + j);
                    }
                    else {
                        str+="j"+j+",";
                        builder.append(parameterTypes[j].getSimpleName() + " j" + j + ",");
                    }
                }
                builder.append("){\n");
                builder.append("\tMethod method=null;\n");
                builder.append("\ttry{\n");
                builder.append("\t\tmethod=" + clazz[i].getSimpleName() + ".class.getDeclaredMethod(\"" + method.getName() + "\",new Class[]{"+s+"});\n");
                builder.append("\t}catch(Exception e){e.printStackTrace();}\n");
                builder.append(str);
//                builder.append(" Class<?>[] parameterTypes = method.getParameterTypes();\n" +
//                        "Object[] args=null;\n"+
//                        "if(parameterTypes!=null){\n"+
//                        "                 args = new Object[parameterTypes.length];\n" +
//                        "                for (int r = args.length - 1; r >= 0; r--) {\n" +
//                        "                    try {\n" +
//                        "                        args[r]=parameterTypes[r].newInstance();\n" +
//                        "                    } catch (Exception e) {\n" +
//                        "                        e.printStackTrace();\n" +
//                        "                    } \n" +
//                        "                }}");
                if (method.getReturnType().getSimpleName()=="void")
                    builder.append("\t\t handler.invoke(method,args)");
                else builder.append("\t\treturn  ("+method.getReturnType().getSimpleName()+ ")handler.invoke(method,args)");
//            builder.append("\t\tSystem.out.println(\"log\");\n");
//            builder.append("\t\ttarget." + method.getName() + "(");
//            for (int i = 0; i < parameterTypes.length; i++) {
//                if (i==parameterTypes.length-1)
//                builder.append("i" + i);
//                else    builder.append("i" + i+",");
//
//            }
                builder.append(";\n\t}\n");
            }
        }



        builder.append("}");
        File file = new File("D:\\cn\\yao\\proxy\\$Proxy.java");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取.java文件路径
//        String fileName = System.getProperty("user.dir")+
//                "\\src\\com\\zjm\\www\\test\\TankTimeProxy.java";

        /**
         * ToolProvider类：该类是为查找工具提供者提供方法，例如，编译器的提供者。）
         * getSystemJavaCompiler：获取此平台提供的 Java™ 编程语言编译器。
         */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        /**
         * getStandardFileManager： 为此工具获取一个标准文件管理器实现的新实例。
         * 参数：
         *    diagnosticListener - 用于非致命诊断信息的诊断侦听器；如果为 null，则使用编译器的默认方法来报告诊断信息
         *    locale - 格式化诊断信息时要应用的语言环境；如果为 null，则使用默认语言环境。
         *    charset - 用于解码字节的字符集；如果为 null，则使用平台默认的字符集
         * 返回：
         *    标准文件管理器
         *
         */
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);

        /**
         * getJavaFileObjects：获取表示给定文件的文件对象。
         * 参数：
         *    files - 文件数组
         * 返回：
         *    文件对象列表
         */
        Iterable units = fileMgr.getJavaFileObjects(file);

        /**
         * getTask：使用给定组件和参数创建编译任务的 future
         * 参数：
         *    out - 用于来自编译器的其他输出的 Writer；如果为 null，则使用 System.err
         *    fileManager - 文件管理器；如果为 null，则使用编译器的标准文件管理器
         *    diagnosticListener - 诊断侦听器；如果为 null，则使用编译器的默认方法报告诊断信息
         *    options - 编译器选项；null 表示没有选项
         *    classes - 类名称（用于注释处理），null 表示没有类名称
         *    compilationUnits - 要编译的编译单元；null 表示没有编译单元
         * 返回：
         *    表示编译的对象
         */
        JavaCompiler.CompilationTask t = compiler.getTask(null, null, null, null, null, units);//编译任务

        //  开始编译
        t.call();

        //  关闭“java编译器”
        try {
            fileMgr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            URL[] urls = new URL[]{
                    new URL("file:D:\\\\")
            };
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> aClass = loader.loadClass("cn.yao.proxy.$Proxy");
            Constructor<?> constructor = aClass.getConstructor(MyInvocationHandler.class);
            return constructor.newInstance(handler);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }
}
