package ustc.sse.connect;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午3:07
 */
public class ClientHandler extends IoHandlerAdapter{

    private String value;
    public ClientHandler(String value){
        this.value = value;
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        session.write(value);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(message.toString());
    }
}
