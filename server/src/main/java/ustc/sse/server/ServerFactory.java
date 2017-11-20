package ustc.sse.server;

/**
 * 长连接服务工厂，用于创建监听服务
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/17
 * Time:下午8:17
 */
public class ServerFactory {

    // 面向客户机的服务
    public static final int CLIENT_SERVER = 1;

    // 面向web管理端的服务
    public static final int WEB_SERVER = 2;

    // 面向客户机服务的端口
    public static final int CLIENT_PORT = 9000;

    // 面向web管理端的端口
    public static final int WEB_PORT = 9001;

    /**
     * 创建服务
     * @param type
     * @return
     */
    public static MinaServer createServer(int type){
        if (type == CLIENT_SERVER){
            return new MinaServer(new ClientHandler(), CLIENT_PORT,true);
        }
        else if (type == WEB_SERVER){
            return new MinaServer(new WebHandler(), WEB_PORT,false);
        }
        return null;
    }

}
