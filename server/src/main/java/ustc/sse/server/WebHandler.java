package ustc.sse.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.mina.core.session.IoSession;
import ustc.sse.tools.dao.ClientDao;

/**
 * 负责处理web端长连接的处理类
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
        String id = jsonObject.getString("client_id");
        response.put("id",id);
        // 通过client id查找对应的session
        manager.write(id,msg);
        session.write(response);
    }
}
