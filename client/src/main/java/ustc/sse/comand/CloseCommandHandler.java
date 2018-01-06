package ustc.sse.comand;
/*
关闭client
 */


import ustc.sse.context.AppContext;

import java.io.IOException;

public class CloseCommandHandler extends SupperCommandHandler implements CommandHandler {
    public CloseCommandHandler(String cmdId, String cmd, String data){
        super(cmdId,cmd,data);
    };
    public Object handler()
    {
        try {
            //与服务器连接的session关闭
            AppContext.session.closeNow();
            System.exit(0);
            this.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void close()throws IOException
    {
        Runtime.getRuntime().exec("shutdown -r -f -t 10");
    }
}
