package com.pxb.rmi.client;

import com.pxb.rmi.server.IRemoteObj;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI Client
 * */
public class RMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        // 获取注册中心
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
        // 查找远程对象
        IRemoteObj remoteObj = (IRemoteObj) registry.lookup("remoteObj");
        // 调用远程方法
        String hello = remoteObj.sayHello("Hello");
        System.out.println(hello);
    }
}
