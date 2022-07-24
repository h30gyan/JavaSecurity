package serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonTest2 {
    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        User user = new User("pxb", "123456");
        // {"@type":"serialize.User","password":"123456","username":"pxb"}
        String userJSon = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(userJSon);

        JSONObject jsonObject = JSON.parseObject("{\"@type\":\"serialize.User\",\"password\":\"123456\",\"username\":\"pxb\"}");
        String name = jsonObject.getClass().getName();
        System.out.println(name);
    }
}
