package ustc.sse.handler;

import org.apache.mina.core.session.IoSession;
import ustc.sse.context.AppContext;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/12/2
 * Time:下午10:24
 */
public class WebSocketHandler extends AbHandler{

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        AppContext.webSocket = session;
    }

    public void handlerMsg(IoSession session, String msg) {

    }
}
