package ustc.sse.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class HttpUtils {
    public static JSONObject get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject res = null;
        try {
            res = JSON.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static JSONObject postJson(String url, String json) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder().post(body).url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject res = null;
        try {
            res = JSON.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
