package ustc.sse.server;

import org.apache.mina.core.session.IoSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * session管理类，用于处理保存和客户机连接的session
 * 单例模式，一个服务器只需要一个session管理对象
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/17
 * Time:下午3:48
 */
public class SessionManager {

    //存放session的字典，用IoSession 的id作为key
    private static Map<Long, IoSession> sessions = new ConcurrentHashMap<Long, IoSession>();
    private static SessionManager manager = null;

    private SessionManager(){ }

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

    /**
     * 向指定客户机发送消息
     * @param id
     * @param msg
     * @return
     */
    public void write(Long id,Object msg){
        IoSession session = sessions.get(id);
        session.write(msg);
    }

    /**
     * 移除指定session
     * @param session
     */
    public void remove(IoSession session){
        sessions.remove(session);
    }

    /**
     * 添加一个session到map中
     * @param id
     * @param session
     */
    public void add(Long id, IoSession session){
        //如果当前session没有，则存储到字典中
        if (sessions.get(id) == null){
            sessions.put(id,session);
        }
    }

    public void writeAll(Object msg){
        for (Long id : sessions.keySet()){
            sessions.get(id).write(msg);
        }
    }


}
