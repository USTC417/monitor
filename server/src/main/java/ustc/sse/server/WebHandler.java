package ustc.sse.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.mina.core.session.IoSession;

/**
 * 负责处理web长连接的处理类
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/17
 * Time:下午8:44
 */
public class WebHandler extends AbHandler{

    public void handlerMsg(IoSession session, Object msg) {

        System.out.println(msg);
        JSONObject jsonObject = JSON.parseObject((String)msg);
        String cmdId = jsonObject.getString("cmd_id");
        JSONObject response = new JSONObject();
        response.put("cmd_id",cmdId);
        response.put("param","我收到了你的消息");
        session.write(response);
    }
}
