package ustc.sse.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/polling.do")
    public void polling(HttpServletRequest request, HttpServletResponse response){

        response.setHeader("Content-type", "text/html;charset=UTF-8");//告知浏览器编码方式;
        response.setCharacterEncoding("UTF-8");
        System.out.println("this is polling");
        //获取前台传入参数
        String data = request.getParameter("data");
        // 将前台传入数据当做json
        JSONObject jsonObject = new JSONObject(data);

        System.out.println(jsonObject.toString());
        // 从json中获取这次操作的id
        String cmdId = jsonObject.getString("cmd_id");
        System.out.println("cmd id is "+cmdId);
        Object msg = service.polling(data, cmdId);
        System.out.println(msg);
        try {
            PrintWriter writer = response.getWriter();
            writer.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
