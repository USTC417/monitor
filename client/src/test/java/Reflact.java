import org.json.JSONObject;
import org.junit.Test;
import ustc.sse.comand.CommandHandler;
import ustc.sse.comand.HandlerFactory;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/26
 * Time:下午11:59
 */
public class Reflact {
    @Test
    public void create(){
        JSONObject object = new JSONObject();
        object.put("cmd_id","123");
        JSONObject data = new JSONObject();
        data.put("cmd","config_info");
        object.put("data",data);
        CommandHandler handler = HandlerFactory.create(object.toString());
        String result = (String) handler.handler();
        System.out.println(result);
    }
}
