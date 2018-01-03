package ustc.sse.connection;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;

import static ustc.sse.tool.AppContext.messageQueue;

/**
 * 与mina连接的session 管理对象
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/19
 * Time:下午2:41
 */
public class SessionManager {

    // 消息队列，用于存放集群服务器发回的消息
    private static IoSession session = null;
    private static SessionManager manager = null;
    private SessionManager(){

    }

    public static SessionManager getInstance(){
        if (manager == null){
            synchronized (SessionManager.class){
                if (manager == null){
                    manager = new SessionManager();
                }
            }
        }
        return manager;
    }

    public void write(Object msg){
        System.out.println("给服务器发消息了");
        session.write(msg);session.write(msg);
    }

    public void addMsgToQueue(Object msg){
        // 把msg 转换成json，并读取出其中的cmd_id
        JSONObject jsonObject = new JSONObject((String) msg);
        String cmdId = jsonObject.getString("cmd_id");
        messageQueue.put(cmdId,msg);
    }

    /**
     * 查询消息队列中的指定命令id的消息
     * @param cmdId
     * @return
     */
    public Object poolMessage(String cmdId){
        return messageQueue.get(cmdId);
    }
    public void initSession(IoSession session){
        SessionManager.session = session;
    }
}
