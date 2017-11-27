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
    /**
     * 发送命令
     * @param msg 命令的字符串 json格式
     * @param cmdId 命令id，由前台生成
     * @return
     */
    public Object send(String msg, String cmdId);

    /**
     * 轮询命令执行结果
     * @param cmdId 命令id 由前台保存
     * @return
     */
    public Object polling(String cmdId);
}
