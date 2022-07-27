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
 * JNDI RMI Server
 */
public class JNDIRMIServer {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        int port = 1099;
        String url = "http://localhost:8080/";
        String objName = "obj";
        Registry registry = LocateRegistry.createRegistry(port);
        // JNDI上下文
        InitialContext initialContext = new InitialContext();
        // 绑定RMI远程对象
//        initialContext.bind("rmi://localhost:1099/remoteObj", new RemoteObjImpl());

        // 绑定对象的引用
        Reference refObj = new Reference("TestRef", "TestRef", url);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(refObj);
        // 将对象的引用绑定到RMI服务上
        registry.bind(objName, referenceWrapper);
        System.out.println("RMI Server Start ...");
        System.out.println("RMI server port: " + port);
    }
}
