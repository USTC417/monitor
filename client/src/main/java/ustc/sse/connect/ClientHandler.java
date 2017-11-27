package ustc.sse.connect;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import ustc.sse.comand.CommandHandler;
import ustc.sse.comand.HandlerFactory;
import ustc.sse.context.AppContext;

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
        AppContext.session = session;
        session.write(value);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(message.toString());
        CommandHandler handler = HandlerFactory.create(message.toString());
        AppContext.session.write(handler.handler());
        System.out.println(message.toString());
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("-------"+cause.toString());
    }
}
