package com.pxb.demo;

import javax.naming.InitialContext;

public class Client {
    public static void main( String[] args ) throws Exception
    {
        InitialContext initialContext = new InitialContext();
        initialContext.lookup("rmi://localhost:1099/remoteObj");
    }
}
