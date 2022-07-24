package serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonTest {
    public static void main(String[] args) {
//        // 通过构造方法创建对象
//        User user = new User("pxb", "123456");
//        // fastjson序列化，将java对象转换为json格式的字符串
//        String userJson = JSON.toJSONString(user);
//        System.out.println(userJson);

        // 反序列化为User对象
        User userObject = JSON.parseObject("{\"password\":\"123456\",\"username\":\"pxb\"}", User.class);
        System.out.println(userObject);
    }
}

