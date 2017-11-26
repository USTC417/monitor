package ustc.sse.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import ustc.sse.manager.SessionManager;

/**
 * 处理长连接消息的抽象类
 * created y chenhanping
 * Designer:chenhanping
 * Date:2017/11/17
 * Time:下午8:19
 */
public abstract class AbHandler extends IoHandlerAdapter{

    protected SessionManager manager;

    public AbHandler(){
        manager = SessionManager.getInstance();
    }
    /**
     * 处理异常
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        session.closeNow();
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        //将session存储到管理器中
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
        //调用抽象方法处理消息
        handlerMsg(session,msg);
        System.out.println("Message written..."+msg);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("session closed "+session.getRemoteAddress());
        session.closeNow();
    }

    /**
     * 处理消息的抽象方法，交由子类实现
     * @param msg
     */
    public abstract void handlerMsg(IoSession session,Object msg);
}
