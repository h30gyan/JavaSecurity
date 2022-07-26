package com.pxb.rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI Server
 */
public class RMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        // 创建远程对象，此时就可以远程通信了，服务端已经开了端口，但是此时客户端不知道是哪个端口
        IRemoteObj remoteObj = new RemoteObjImpl();
        // 创建注册中心
        Registry registry = LocateRegistry.createRegistry(1099);
        // 注册中心绑定远程对象
        registry.bind("remoteObj", remoteObj);
    }
}
