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
        String loaderClass = ClassLoader.getSystemClassLoader().getClass().toString();
        System.out.println("ClassLoader.getSystemClassLoader()"+loaderClass);
        LaunchedURLClassAgent.modifyClass(ClassLoader.getSystemClassLoader());

        System.out.println("Agent.premain end");
    }


}
