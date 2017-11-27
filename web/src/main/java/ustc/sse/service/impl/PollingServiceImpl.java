package ustc.sse.service.impl;

import org.springframework.stereotype.Service;
import ustc.sse.connection.ConnectorManager;
import ustc.sse.connection.MessageManager;
import ustc.sse.service.PollingService;

/**命令发送/结果轮询服务
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/19
 * Time:下午3:12
 */
@Service
public class PollingServiceImpl implements PollingService {

    MessageManager manager = new MessageManager();
    /**
     * 发送命令接口
     * @param msg
     * @param cmdId
     * @return
     */
    public Object send(String msg, String cmdId) {

        // 获取连接实例
        return manager.sendMessage(msg, cmdId);
    }

    /**
     * 轮询消息
     * @param cmdId 命令id 由前台保存
     * @return
     */
    public Object polling(String cmdId) {
        return manager.polling(cmdId);
    }
}
