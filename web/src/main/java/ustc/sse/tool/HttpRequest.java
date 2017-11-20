package ustc.sse.tool;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * Created by chen on 2016/12/11.
 * 发送请求
 */
public class HttpRequest {

    public String sendRequest(String urlStr, Map<String,String> params){

        String response = "";
        try {

            HttpClient httpClient = new HttpClient();
            httpClient.getParams().setContentCharset("UTF-8");
            PostMethod postMethod = new PostMethod(urlStr);

            Iterator iter = params.entrySet().iterator();
            //封装参数
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                postMethod.setParameter(key,value);
            }

            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.executeMethod(postMethod);

            InputStream inputStream = postMethod.getResponseBodyAsStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String temp = "";
            //循环接受返回的字符串
            while ((temp = reader.readLine())!=null){
                response += temp;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
