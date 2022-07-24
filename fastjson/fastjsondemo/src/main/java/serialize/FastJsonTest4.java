package serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;

// 两种反序列化的方法的区别
public class FastJsonTest4 {
    public static void main(String[] args) {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String jsonStr = "{\"@type\":\"serialize.User\",\"password\":\"123456\",\"username\":\"pxb\"}";
        // parseObject方法反序列化
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        System.out.println(jsonObject);
        // parse方法反序列化
        Object parse = JSON.parse(jsonStr);
        System.out.println(parse);

    }
}
