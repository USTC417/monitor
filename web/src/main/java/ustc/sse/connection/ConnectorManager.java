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


    /**
     * 发送消息，并返回消息的回复，如果没有3s内回复内，则算作没有及时回复，返回null
     * @param msg
     * @param cmdId
     * @return 回复
     */
    public Object sendMessage(Object msg,String cmdId){
        manager.write(msg);
        while (!timeOut()){
            // 没有超时，则查询消息队列中是否有指定命令id的回复消息
            Object response = null;
            if ((response = manager.poolMessage(cmdId))!=null){
                count = 0;
                return response;
            }
        }
        return null;
    }

    /**
     * 判断是否超时
     * @return
     */
    private boolean timeOut(){
        try {
            //停顿0.01s
            Thread.sleep(10);
            if (count*0.01 < 3){
                // 3秒以内不算超时
                count++;
                return false;
            }
            else {
                //超时
                count = 0;
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count = 0;
        return false;
    }

    public static final int PORT = 9001;
    private int count = 0;

}
