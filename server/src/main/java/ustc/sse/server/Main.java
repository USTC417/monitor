package ustc.sse.server;


import ustc.sse.websocket.ConnectWebSocket;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午3:24
 */
public class Main {

    public static void main(String args[]){

        //生产负责连接客户机的服务
        ServerFactory.createServer(ServerFactory.CLIENT_SERVER).startServer();
        // 生产负责连接web管理端的服务
        ServerFactory.createServer(ServerFactory.WEB_SERVER).startServer();
        // websocket
        new ConnectWebSocket().start();
    }
}
