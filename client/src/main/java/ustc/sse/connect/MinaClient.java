package ustc.sse.connect;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.json.JSONObject;
import ustc.sse.tools.net.HttpRequest;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午3:06
 */
public class MinaClient {

    private int HEART_BEAT = 10;
    private String clientId;

    public MinaClient(){
        init();
    }

    public void start(){

        final IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"))));

        JSONObject object = new JSONObject();
        object.put("event","init");
        JSONObject data = new JSONObject();
        data.put("client_id",clientId);
        object.put("data",data);
        connector.setHandler(new ClientHandler(object.toString()));
        ClientKeepAliveFactoryImpl keepAliveFactory = new ClientKeepAliveFactoryImpl();
        KeepAliveFilter filter = new KeepAliveFilter(keepAliveFactory, IdleStatus.BOTH_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
        filter.setRequestInterval(HEART_BEAT);
        filter.setRequestTimeout(5);
        //connector.getFilterChain().addLast("heartbeat",filter);
        connector.connect(new InetSocketAddress("localhost",9000));

        //connector.dispose();

    }

    /**
     * 创建连接时需要发送的字符串内容
     * 从文件中读取clientid，如果有，则直接发送
     * 如果没有，则说明是第一次连接，应该把本机的信息发送并把clientid置为-1
     * @return
     */
    private Map<String,String> createParam(){
        Map<String,String> params = new HashMap<String, String>();
        JSONObject request = new JSONObject();
        //从文件中读取client id
        JSONObject data = new JSONObject();
        data.put("cpu","intel");
        data.put("system","Max os 10.12");
        data.put("ram",8.0);
        data.put("storage",256.5);
        params.put("data",data.toString());
        System.out.println(params);
        return params;
    }

    public void init(){

        Map<String,String> params = createParam();
        HttpRequest request = new HttpRequest();
        clientId = request.sendRequest("http://localhost:8080/init",params);
    }
}
