package ustc.sse.tools.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ustc.sse.tools.entity.ClientEntity;

import java.util.UUID;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/24
 * Time:上午9:52
 */
public class Util {

    public static String createId(){
        String uuid = UUID.randomUUID().toString();
        String uuids[] = uuid.split("-");
        StringBuffer sb = new StringBuffer();
        for (int j=0;j<uuids.length;j++){
            sb.append(uuids[j]);
        }
        return sb.toString();
    }

    public static Object parseJavaObject(String data,Class c){
        return JSON.toJavaObject(JSON.parseObject(data.toString()),c);
    }
}
