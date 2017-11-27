package ustc.sse.comand;

import org.json.JSONObject;

/**
 * 配置命令的处理类，实现命令处理接口
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/26
 * Time:下午8:47
 */
public class ConfCommandHandler extends SupperCommandHandler implements CommandHandler{

    public ConfCommandHandler(String cmdId, String cmd, String data) {
        super(cmdId, cmd, data);
    }

    public Object handler() {
        JSONObject object = new JSONObject();
        JSONObject jsonData = new JSONObject();
        jsonData.put("status",200);
        jsonData.put("cmd_id",cmdId);
        object.put("data",jsonData);
        object.put("cmd",cmd);
        object.put("client_id","");
        object.put("event","reply");
        return object;
    }
}
