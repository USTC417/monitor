package ustc.sse.comand;

import org.json.JSONObject;

/**
 * 命令处理类的父类
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/26
 * Time:下午10:21
 */
public class SupperCommandHandler {

    protected String cmd;
    protected String cmdId;
    protected String data;

    public SupperCommandHandler(String cmdId, String cmd, String data) {
        this.cmd = cmd;
        this.cmdId = cmdId;
        this.data = data;
    }
}
