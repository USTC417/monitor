package ustc.sse.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ustc.sse.service.PollingService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 轮询控制器，负责转发给服务器的信息并接收返回消息
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/19
 * Time:下午2:34
 */
@Controller
public class PollingController {


    @Resource
    private PollingService service;

    @RequestMapping("/send_cmd.do")
    public void send(HttpServletRequest request, HttpServletResponse response){

        response.setHeader("Content-type", "text/html;charset=UTF-8");//告知浏览器编码方式;
        response.setCharacterEncoding("UTF-8");
        System.out.println("this is send");
        //获取前台传入参数
        String data = request.getParameter("data");

        // 从json中获取这次操作的id
        String cmdId = new JSONObject(data).getString("cmd_id");
        System.out.println("cmd id is "+cmdId);
        Object msg = service.send(data, cmdId);
        // 把cmd_id封装到data中
        System.out.println(msg);
        try {
            PrintWriter writer = response.getWriter();
            writer.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/polling.do",method = RequestMethod.GET)
    @ResponseBody
    public String polling(String cmdId){
        System.out.println("this is polling");
        String result = (String) service.polling(cmdId);
        return result;
    }
}
