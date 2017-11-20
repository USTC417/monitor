package ustc.sse.connection;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午3:07
 */
public class ClientHandler extends IoHandlerAdapter{

    private SessionManager manager;

    public ClientHandler() {
        manager = SessionManager.getInstance();
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("初始化session");
        manager.initSession(session);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("收到服务器消息"+message.toString());
        manager.addMsgToQueue(message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println(cause.toString());
    }
}
