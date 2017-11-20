package ustc.sse.connect;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/18
 * Time:上午11:13
 */
public class ClientKeepAliveFactoryImpl implements KeepAliveMessageFactory{


    private String response_msg = "0x12";
    public boolean isRequest(IoSession ioSession, Object o) {
        return false;
    }

    public boolean isResponse(IoSession ioSession, Object o) {
        if (o.toString().equals(response_msg)){
            return true;
        }
        return false;
    }

    public Object getRequest(IoSession ioSession) {
        return null;
    }

    public Object getResponse(IoSession ioSession, Object o) {
        return "get response";
    }
}
