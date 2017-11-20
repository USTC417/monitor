package ustc.sse.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * 处理客户机消息，实现处理消息的抽象方法
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午2:54
 */
public class ClientHandler extends AbHandler{


    // 存储客户机连接session的管理类
    private SessionManager manager;

    public ClientHandler() {
        manager = SessionManager.getInstance();
    }

    @Override
    public void handlerMsg(IoSession session,Object msg) {
        //将session存储到管理器中
        manager.add(session.getId(),session);
        System.out.println(session.getId());
    }
}
