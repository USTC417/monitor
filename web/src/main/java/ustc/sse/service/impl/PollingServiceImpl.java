package ustc.sse.service.impl;

import org.springframework.stereotype.Service;
import ustc.sse.connection.ConnectorManager;
import ustc.sse.service.PollingService;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/19
 * Time:下午3:12
 */
@Service
public class PollingServiceImpl implements PollingService {

    public Object polling(String msg,String cmdId) {

        // 获取连接实例
        ConnectorManager manager = ConnectorManager.getInstance();
        return manager.sendMessage(msg, cmdId);
    }
}
