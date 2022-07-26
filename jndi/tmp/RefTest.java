package com.pxb.test;

import java.io.IOException;

public class RefTest {
    static {
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
