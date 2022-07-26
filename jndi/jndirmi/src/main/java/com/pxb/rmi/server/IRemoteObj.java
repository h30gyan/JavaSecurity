package com.pxb.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程对象接口，客户端和服务端都要定义的接口
 * */
public interface IRemoteObj extends Remote {
    //sayHello就是客户端要调用的方法，需要抛出RemoteException
    String sayHello(String keywords) throws RemoteException;
}

