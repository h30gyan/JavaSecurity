package com.pxb.jndirmi.client;

import com.pxb.rmi.server.IRemoteObj;
import com.pxb.rmi.server.RemoteObjImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * JNDI RMI Client
 * */
public class JNDIRMIClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        InitialContext initialContext = new InitialContext();
        // 底层相当于原生的RMI 如果该字段可控，则可以让服务器去恶意服务器上查找对象，执行恶意代码
        // 当客户端通过lookup获取远程对象时，获取的是一个Reference存根（Stub），由于是Reference的存根，所以客户端会先在本地的classpath
        // 中去查找是否存在类TestRef（Reference的第一个参数），如果不存在不则去指定的url动态加载，并且调用TestRef（Reference的第二个参数）
        // 的无参构造方法，执行恶意代码。也可以在static中写恶意代码，因为static中的代码在class文件被加载的时候就会立即执行。
        initialContext.lookup("rmi://localhost:1099/obj");
//        IRemoteObj remoteObj = (IRemoteObj)initialContext.lookup("rmi://localhost:1099/remoteObj");
//        System.out.println(remoteObj.sayHello("Hello"));
    }
}
