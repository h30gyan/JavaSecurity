package serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonTest3 {
    public static void main(String[] args) {
        // 1.2.25及以上的版本，需要手动开启AutoType
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 创建一个apple对象
        Apple apple = new Apple();
        apple.setCnName("苹果");
        // 序列化
        String appleJson = JSON.toJSONString(apple, SerializerFeature.WriteClassName);
        System.out.println(appleJson);
        // 反序列化
//        Apple apple1 = (Apple) JSON.parse("{\"cnName\":\"苹果\"}");
    }
}

interface Fruit {
}

class Apple implements Fruit {
    private String cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}

class Orange implements Fruit {
    private String cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}