package ustc.sse.comand;

import org.json.JSONObject;
import ustc.sse.tools.util.InPropertiesHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * 生成命令处理对象的工厂
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/26
 * Time:下午8:51
 */
public class HandlerFactory {

    /**
     * 根据类别生成处理对象
     * @param cmd 命令名称
     * @return
     */
    public static CommandHandler create(String msg){

        JSONObject object = new JSONObject(msg);
        String cmdId = object.getString("cmd_id");
        String cmd = object.getString("cmd");
        // 读取配置文件
        InPropertiesHelper helper = new InPropertiesHelper("classbean.properties");
        String className = helper.getValueByKey(cmd);
        if (className == null){
            return null;
        }
        try {
            Class c = Class.forName(className);
            Constructor constructor = c.getConstructor(String.class,String.class, String.class);
            return (CommandHandler) constructor.newInstance(cmdId,cmd,msg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
