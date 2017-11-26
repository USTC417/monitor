package ustc.sse.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * create by chen on 2017/8/10
 * 读取配置文件的帮助类
 * 可以读取的是在jar包中的配置文件
 */
public class InPropertiesHelper {

    protected Properties properties;

    protected InputStream inputStream;

    //配置文件的文件名
    protected String propertiesFileName;
    public InPropertiesHelper(){
        properties = new Properties();
    }
    public InPropertiesHelper(String propertiesFileName) {
        properties = new Properties();
        //初始化配置文件
        inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName);

    }

    /**
     * 更改配置文件
     * @param propertiesFileName
     */
    public void setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        //重新加载配置文件
        inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName);
    }

    /**
     * 获取指定键的值
     * @param key
     * @return
     */
    public String getValueByKey(String key){

        try {
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
