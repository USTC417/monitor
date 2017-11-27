package ustc.sse.connection;

import com.alibaba.fastjson.JSONObject;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 连接集群服务器的帮助类
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/19
 * Time:下午2:49
 */
public class ConnectorManager {

    private static ConnectorManager helper = null;
    private IoConnector connector;
    private SessionManager manager;

    private ConnectorManager(){
        manager = SessionManager.getInstance();
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.setHandler(new ClientHandler());
        connector.connect(new InetSocketAddress("localhost",PORT));

    }

    public static ConnectorManager getInstance(){
        if (helper == null){
            synchronized (ConnectorManager.class){
                if (helper == null){
                    helper = new ConnectorManager();
                    return helper;
                }
            }
        }
        return helper;
    }



    public static final int PORT = 9001;

}
