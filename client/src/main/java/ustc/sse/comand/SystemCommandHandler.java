package ustc.sse.comand;

import org.json.JSONObject;
/*
获取client的系统信息：名称，ip，系统名
 */

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemCommandHandler extends SupperCommandHandler implements CommandHandler {
    public SystemCommandHandler(String cmdId, String cmd, String data) {
        super(cmdId, cmd, data);
    }
    String ip;
    String clientID;
    String hostName;
    public Object handler()
    {
        JSONObject object=new JSONObject();
        JSONObject objectdata=new JSONObject();
        this.getHostName();
        objectdata.put("IP",ip);
        objectdata.put("CLIENTID",clientID);
        objectdata.put("name",hostName);
        object.put("data",objectdata);
        return object;
    }
    public void getHostName()
    {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        clientID= System.getProperty("os.name");//系统名称
        ip=addr.getHostAddress().toString(); //获取本机ip
        hostName=addr.getHostName().toString(); //获取本机计算机名称
    }

}
