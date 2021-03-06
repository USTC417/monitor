package ustc.sse.manager;

import org.apache.mina.core.session.IoSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ustc.sse.context.AppContext.sessions;

/**
 * session管理类，用于处理保存和客户机连接的session
 * 单例模式，一个服务器只需要一个session管理对象
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/17
 * Time:下午3:48
 */
public class SessionManager {


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

    /**
     * 向指定客户机发送消息
     * @param id
     * @param msg
     * @return
     */
    public void write(String id,Object msg){
        System.out.println(id);
        IoSession session = sessions.get(id);
        if (session == null){
            System.out.println("session 为null");
        }
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
    public void add(String id, IoSession session){
        //如果当前session没有，则存储到字典中
        if (sessions.get(id) == null){
            sessions.put(id,session);
        }
    }

    public void writeAll(Object msg){
        for (String id : sessions.keySet()){
            sessions.get(id).write(msg);
        }
    }


}
