package ustc.sse.websocket;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import ustc.sse.context.AppContext;

public class ConnectWebSocket extends WebSocketServer{

	// 服务器监听端口
	private static final int PORT = 9003;

	public ConnectWebSocket(InetSocketAddress address) {
		super(address);
	}

	public ConnectWebSocket(){
        super(new InetSocketAddress(PORT));

    }
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

        System.out.println("web socket open!");
        // 存储到内存中
        AppContext.webSocketSet.add(webSocket);
	}

	public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        AppContext.webSocketSet.remove(webSocket);
	}

	public void onMessage(WebSocket webSocket, String s) {

	}

	public void onError(WebSocket webSocket, Exception e) {
        AppContext.webSocketSet.remove(webSocket);
        System.out.println("web socket error");
    }



}
