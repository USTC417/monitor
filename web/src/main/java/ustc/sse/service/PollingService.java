package ustc.sse.service;

import org.springframework.stereotype.Service;

/**
 * 轮询服务接口
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/19
 * Time:下午3:09
 */
public interface PollingService {
    public Object polling(String msg,String cmdId);
}
