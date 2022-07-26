package com.pxb.jndirmi.server;

import com.pxb.rmi.server.RemoteObjImpl;
import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * JNDI Server
 */
public class JNDIRMIServer {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        // JNDI上下文
        InitialContext initialContext = new InitialContext();
        // 绑定RMI远程对象
//        initialContext.bind("rmi://localhost:1099/remoteObj", new RemoteObjImpl());

        // 绑定对象的引用
        Reference refObj = new Reference("TestRef", "TestRef", "http://127.0.0.1:8080/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(refObj);
        // 将对象的引用绑定到RMI服务上
        registry.bind("TestRef", referenceWrapper);
    }
}
