package ustc.sse.comand;
/*
重启client，并且重启MONITOR
 */

import ustc.sse.context.AppContext;

import java.io.IOException;

public class RestartCommandHandler extends SupperCommandHandler implements CommandHandler {

    public RestartCommandHandler(String cmdId, String cmd, String data) {
        super(cmdId, cmd, data);
    }

    public Object handler() {
//        this.restart();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        this.startApp();
        AppContext.session.closeNow();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void restart()
    {
        try {
            Runtime.getRuntime().exec("shutdown -r -f -t 10");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startApp()
    {
        try {
            Runtime.getRuntime().exec("c://monitor.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
