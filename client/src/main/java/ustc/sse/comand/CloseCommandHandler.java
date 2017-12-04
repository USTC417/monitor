package ustc.sse.comand;
/*
关闭client
 */


import java.io.IOException;

public class CloseCommandHandler extends SupperCommandHandler implements CommandHandler {
    CloseCommandHandler(String cmdId, String cmd, String data){
        super(cmdId,cmd,data);
    };
    public Object handler()
    {
        try {
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
