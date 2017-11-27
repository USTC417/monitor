package ustc.sse.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;
import ustc.sse.context.AppContext;
import ustc.sse.tools.dao.ClientDao;
import ustc.sse.tools.entity.ClientEntity;
import ustc.sse.tools.util.Util;

import java.util.Date;

/**
 * 处理客户机消息，实现处理消息的抽象方法
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午2:54
 */
public class ClientHandler extends AbHandler{


    public ClientHandler() {

    }

    @Override
    public void handlerMsg(IoSession session,String msg) {

        // 收到消息之后应该查询当前消息是否包含client id，
        // 若有则说明该客户机已经得到了自己的id，
        // 否则需要生成一个唯一id并存入map
        JSONObject request = new JSONObject(msg);
        // 先获取消息的event类型
        String event = request.getString("event");
        JSONObject data = request.getJSONObject("data");
        if (event.equals("init")){
            // 初始化客户机
            String clientId = data.getString("client_id");
            System.out.println("clientId = "+clientId);
            AppContext.sessions.put(clientId,session);
        }else {
            // 回复命令的消息，直接转发web端
            AppContext.webSession.write(data);
        }

    }


}
