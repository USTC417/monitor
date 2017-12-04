package ustc.sse.context;

import org.apache.mina.core.session.IoSession;
import ustc.sse.manager.SessionManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 静态数据
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/26
 * Time:下午8:39
 */
public class AppContext {

    // web端长连接
    public static IoSession webSession;

    // 客户机的连接session
    public static Map<String, IoSession> sessions = new ConcurrentHashMap<String, IoSession>();

    // web端的websocket
    public static IoSession webSocket;
}
