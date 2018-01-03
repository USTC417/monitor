package ustc.sse.handler;

import org.apache.mina.core.session.IoSession;
import org.java_websocket.WebSocket;
import org.json.JSONObject;
import ustc.sse.context.AppContext;
import ustc.sse.tools.dao.ClientDao;

import java.util.Map;

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
            // 向web socket 发送消息
            JSONObject object = new JSONObject();
            object.put("client_id",clientId);
            object.put("status","1");
            try {
                for (WebSocket web : AppContext.webSocketSet){
                    web.send(object.toString());
                }
                System.out.println("发送成功");
            }catch (Exception e){
                System.out.println(e.toString());
            }


        }else {
            // 回复命令的消息，直接转发web端
            AppContext.webSession.write(data);
        }

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // 发送消息告知客户端关闭
        JSONObject object = new JSONObject();
        object.put("status",0);
        String clientId = "";
        for (Map.Entry<String, IoSession> entry : AppContext.sessions.entrySet()){
            if (entry.getValue() == session){
                object.put("client_id",entry.getKey());
                clientId = entry.getKey();
                AppContext.sessions.remove(clientId);
                break;
            }
        }
        for (WebSocket web : AppContext.webSocketSet){
            web.send(object.toString());
        }
        // 在数据库中更改客户机的状态
        ClientDao dao = new ClientDao();
        dao.updateClient(0 ,clientId);
    }
}
