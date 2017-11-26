package ustc.sse.connection;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/26
 * Time:下午2:16
 */
public class MessageManager {

    private static SessionManager manager = SessionManager.getInstance();
    /**
     * 发送消息，并返回消息的回复，如果没有3s内回复内，则算作没有及时回复，返回null
     * @param msg
     * @param cmdId
     * @return 回复
     */
    public Object sendMessage(Object msg,String cmdId){
        manager.write(msg);
        return "200";
    }

    /**
     * 轮询获得消息
     * @param cmdId
     * @return 轮询结果，超时返回null
     */
    public Object polling(String cmdId) {
        while (!timeOut()){
            // 没有超时，则查询消息队列中是否有指定命令id的回复消息
            Object response = null;
            if ((response = manager.poolMessage(cmdId))!=null){
                return response;
            }
        }
        return null;
    }

    /**
     * 判断是否超时
     * @return
     */
    private boolean timeOut(){
        int count = 0;
        try {
            //停顿0.01s
            Thread.sleep(10);
            if (count*0.01 < 3){
                // 3秒以内不算超时
                count++;
                return false;
            }
            else {
                //超时
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

}
