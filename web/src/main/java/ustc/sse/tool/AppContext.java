package ustc.sse.tool;

import org.apache.mina.core.session.IoSession;

import java.util.HashMap;
import java.util.Map;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/27
 * Time:上午12:13
 */
public class AppContext {
    public static Map<String,Object> messageQueue = new HashMap<String, Object>();
    public static IoSession session;
}
