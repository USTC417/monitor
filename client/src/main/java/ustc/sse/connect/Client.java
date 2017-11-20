package ustc.sse.connect;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午3:06
 */
public class Client {

    private int HEART_BEAT = 10;
    public void client(){

        final IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.setHandler(new ClientHandler("this is your msg"));
        ClientKeepAliveFactoryImpl keepAliveFactory = new ClientKeepAliveFactoryImpl();
        KeepAliveFilter filter = new KeepAliveFilter(keepAliveFactory, IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
        filter.setRequestInterval(HEART_BEAT);
        filter.setRequestTimeout(5);
        connector.getFilterChain().addLast("heartbeat",filter);
        connector.connect(new InetSocketAddress("localhost",9000));

        //connector.dispose();

    }
}
