package serialize;

import java.io.IOException;

public class User {
    private String username;
    private String password;

    public User() {
        System.out.println("调用了无参构造方法");
//        try {
//            Runtime.getRuntime().exec("calc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("调用了有参构造方法");
    }

    public String getUsername() {
        System.out.println("调用了getUsername()");
        return username;
    }

    public void setUsername(String username) {
        System.out.println("调用了setUsername()");
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.username = username;
    }

    public String getPassword() {
        System.out.println("调用了getPassword()");
//        try {
//            Runtime.getRuntime().exec("calc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return password;
    }

    public void setPassword(String password) {
        System.out.println("调用了setPassword()");
        this.password = password;
    }

}
