package ustc.sse.connect;


/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/4
 * Time:下午3:44
 */
public class ClientMain {

    public static void main(String args[]){
//        for (int i=0;i<400;i++){
//            Thread thread = new Thread(){
//                @Override
//                public void run() {
//                    new MinaClient().start();
//                }
//            };
//            thread.start();
//        }
        new MinaClient().start();
    }
}
