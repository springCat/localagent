package org.springcat.localagent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import java.lang.instrument.Instrumentation;


public class Agent
{

    public static void premain(String args, Instrumentation inst) throws Exception
    {
        System.out.println("Agent.premain start");
//        ClassPool pool = ClassPool.getDefault();
//        //for server start
////        CtClass ctClass = pool.get("org.springframework.boot.loader.LaunchedURLClassLoader");
//
//        //for local app start in idea class sun.misc.Launcher$AppClassLoader
//        CtClass ctClass = pool.get("org.springframework.boot.devtools.restart.classloader.RestartClassLoader");
//
//        CtConstructor[] ctConstructors = ctClass.getDeclaredConstructors();
//        String after = "System.out.println(\"LaunchedURLClassAgent.modifyClass success\");\n" +
//                "org.springcat.localagent.LaunchedURLClassAgent.modifyClass(this);\n";
//        for (CtConstructor constructor : ctConstructors)
//        {
//            constructor.insertAfter(after);
//        }
//        ctClass.toClass();
        System.out.println("ClassLoader.getSystemClassLoader()"+ClassLoader.getSystemClassLoader().getClass().toString());
        LaunchedURLClassAgent.modifyClass(ClassLoader.getSystemClassLoader());

        System.out.println("Agent.premain end");
    }


}
