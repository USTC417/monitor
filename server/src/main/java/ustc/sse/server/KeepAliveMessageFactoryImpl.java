package ustc.sse.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 实现心跳机制
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/18
 * Time:上午10:39
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory{

    private String request_msg = "0x11";
    private String response_msg = "0x12";
    public boolean isRequest(IoSession ioSession, Object o) {
        if (o.toString().equals(request_msg)){
            return true;
        }
        return false;
    }

    public boolean isResponse(IoSession ioSession, Object o) {
        return false;
    }

    public Object getRequest(IoSession ioSession) {
        return "0x11";
    }

    public Object getResponse(IoSession ioSession, Object o) {
        return null;
    }
}
