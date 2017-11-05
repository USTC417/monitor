package ustc.sse.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/10/24
 * Time:上午8:41
 */
public class MainServer {

    public static void server(){
        // 监听tcp连接
        IoAcceptor acceptor = new NioSocketAcceptor();
        //设置拦截器
        acceptor.getFilterChain().addLast("logger",new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        //设置server 处理对象
        acceptor.setHandler(new TimeHandler());
        // 5. 设置Session的对应I/O processor读缓存区大小2048，通常这个参数不需要设置
        acceptor.getSessionConfig().setReadBufferSize(2048);
        // 6. 设置空闲时间， 这里的BOTH_IDLE指EADER_IDLE和WRITER_IDLE都为10秒
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        //设置监听端口
        try {
            acceptor.bind(new InetSocketAddress(9000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
