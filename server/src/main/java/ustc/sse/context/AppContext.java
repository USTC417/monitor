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

    public static IoSession webSession;
    public static Map<String, IoSession> sessions = new ConcurrentHashMap<String, IoSession>();
}
