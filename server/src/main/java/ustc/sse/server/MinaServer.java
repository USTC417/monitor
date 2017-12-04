package ustc.sse.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import ustc.sse.handler.AbHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/10/24
 * Time:上午8:41
 */
public class MinaServer {

    // 服务的消息处理对象
    private AbHandler handler;
    // 服务所在端口
    private int port;

    IoAcceptor acceptor;

    /** 30秒后超时 */
    private static final int IDEL_TIMEOUT = 30;
    /** 15秒发送一次心跳包 */
    private static final int HEART_BEAT = 15;

    private boolean isHeartBeat;

    public MinaServer(AbHandler handler,int port,boolean isHeartBeat){
        this.handler = handler;
        this.port = port;
        this.isHeartBeat = isHeartBeat;
        acceptor = new NioSocketAcceptor();
    }

    public  void startServer(){

        //设置拦截器
        acceptor.getFilterChain().addLast("logger",new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        if (isHeartBeat) {
            KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
            KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.READER_IDLE);
            heartBeat.setForwardEvent(true);
            //设置心跳频率
            heartBeat.setRequestInterval(HEART_BEAT);
            //acceptor.getFilterChain().addLast("heartbeat", heartBeat);

        }
        //设置server 处理对象
        acceptor.setHandler(handler);
        // 5. 设置Session的对应I/O processor读缓存区大小2048，通常这个参数不需要设置
        //acceptor.getSessionConfig().setReadBufferSize(2048);
        // 6. 设置空闲时间， 这里的BOTH_IDLE指EADER_IDLE和WRITER_IDLE都为10秒
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        //设置监听端口
        try {
            acceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
