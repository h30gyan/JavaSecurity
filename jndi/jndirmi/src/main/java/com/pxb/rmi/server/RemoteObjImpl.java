package com.pxb.rmi.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服务端需要实现该接口，实现具体的功能
 * */
public class RemoteObjImpl extends UnicastRemoteObject implements IRemoteObj {

    public RemoteObjImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String keywords) throws RemoteException {
//        try {
//            Runtime.getRuntime().exec("calc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return keywords + "World";
    }
}
