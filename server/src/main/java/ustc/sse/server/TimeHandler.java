package ustc.sse.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午2:54
 */
public class TimeHandler extends IoHandlerAdapter{

    /**
     * 处理异常
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    /**
     * 对接收到的消息进行处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session,message);
        String msg = message.toString();
        session.write("已收到消息");
        System.out.println("Message written..."+msg);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println(session.getIdleCount(status));
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("session closed "+session.getRemoteAddress());
    }
}
