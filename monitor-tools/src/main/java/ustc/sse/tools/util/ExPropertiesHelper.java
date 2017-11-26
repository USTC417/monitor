package ustc.sse.tools.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * 只能读取在外部的配置文件，也就是在打jar包之后必须将配置文件放在和jar同一个目录下
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/25
 * Time:下午2:01
 */
public class ExPropertiesHelper extends InPropertiesHelper{
    public ExPropertiesHelper(String propertiesFileName) {
        //初始化配置文件
        try {
            inputStream = new FileInputStream(propertiesFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
