package ustc.sse.handler;

import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;
import ustc.sse.context.AppContext;

/**
 * 负责处理web端长连接的处理类
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/17
 * Time:下午8:44
 */
public class WebHandler extends AbHandler{


    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // 将web 端的连接session存储下来
        AppContext.webSession = session;
    }

    public void handlerMsg(IoSession session, String msg) {

        System.out.println("web端发来的消息是："+msg);
        JSONObject jsonObject = new JSONObject(msg);
        String id = jsonObject.getString("client_id");
        // 通过client id查找对应的session
        manager.write(id,msg);
    }
}
