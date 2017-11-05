package ustc.sse.connect;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
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

    public void client(){

        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        connector.setHandler(new ClientHandler("你好！\r\n 大家好！"));
        connector.connect(new InetSocketAddress("localhost",9000));
    }
}
