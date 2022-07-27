package com.pxb.demo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import java.util.Hashtable;

public class Server {
    public static void main(String[] args) throws Exception{
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL,
                "rmi://localhost:1099");
        InitialContext initialContext = new InitialContext(env);
        Reference refObj = new Reference("TestRef", "TestRef", "http://localhost:7777/");
        initialContext.bind("remoteObj", refObj);
    }
}
