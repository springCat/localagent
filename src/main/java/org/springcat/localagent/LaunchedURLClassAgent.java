package org.springcat.localagent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;


public class LaunchedURLClassAgent
{

    public static void modifyClass(ClassLoader loader) throws NotFoundException, CannotCompileException
    {


        String before= " System.out.println(\"enhance local route\");\n"+
                "   if (key instanceof feign.Request) {\n" +
                "      cn.hutool.setting.Setting localRoute = cn.hutool.setting.SettingUtil.get(\"localServer.setting\");\n" +
                "      feign.RequestTemplate requestTemplate = ((feign.Request) key).requestTemplate();\n" +
                "      java.net.URL url = cn.hutool.core.util.URLUtil.url(requestTemplate.url());\n" +
                "      //严格模式,工程名+接口名\n" +
                "      String address = localRoute.get(\"interfaces\", url.getAuthority()+url.getPath());\n" +
                "      //简易模式.接口名\n" +
                "      if(cn.hutool.core.util.StrUtil.isBlank(address)) {\n" +
                "        address = localRoute.get(\"interfaces\", url.getPath().substring(1));\n" +
                "      }\n" +
                "      //单个工程替换模式\n" +
                "      if(cn.hutool.core.util.StrUtil.isBlank(address)) {\n" +
                "        address = localRoute.get(\"servers\", requestTemplate.feignTarget().name());\n" +
                "      }\n" +
                "      if(!cn.hutool.core.util.StrUtil.isBlank(address)) {\n" +
                "          String[] str = cn.hutool.core.util.StrUtil.split(address, \":\");\n" +
                "          System.out.println(url + \"redirect to local adresss \"+address);\n" +
                "          String ip = cn.hutool.core.util.StrUtil.emptyToDefault(str[0],\"127.0.0.1\");\n" +
                "          System.out.println(\"redirect to ip \"+ip);\n" +
                "          int port = Integer.parseInt(str[1]);\n"+
                "          System.out.println(\"redirect to port \"+port);\n" +
                "          return new com.netflix.loadbalancer.Server(ip, port);\n" +
                "      }\n"+
                "    }";
        JavassistAop route1 = new JavassistAop("com.netflix.loadbalancer.PredicateBasedRule","choose");
        route1.setBefore(before);
        handleOne(loader, route1);

        JavassistAop route2 = new JavassistAop("com.netflix.loadbalancer.BaseLoadBalancer","choose");
        route2.setBefore(before);
        handleOne(loader, route2);

    }

    public static void handleOne(ClassLoader loader,JavassistAop route) throws NotFoundException, CannotCompileException{
        ClassPool classPool = ClassPool.getDefault();
        classPool.appendClassPath(new LoaderClassPath(loader));
        CtClass ctClass = classPool.get(route.getCls());
        CtMethod ctMethod = ctClass.getDeclaredMethod(route.getMethod());
        ctMethod.insertBefore(route.getBefore());
        ctClass.toClass(loader, ctClass.getClass().getProtectionDomain());
    }

}

