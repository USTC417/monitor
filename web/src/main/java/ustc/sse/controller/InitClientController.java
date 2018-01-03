package ustc.sse.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ustc.sse.service.InitService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/** 第一次连接客户端 返回一个标识符
 * created by chenhanping
 * Deigner:chenhanping
 * Date:2017/11/24
 * Time:上午11:55
 */
@Controller
public class InitClientController {

    @Resource
    private InitService service;
    @RequestMapping(value = "/init",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public
    @ResponseBody
    String init(HttpServletRequest request)
    {
        JSONObject object = new JSONObject();
        try {
            JSONObject data = new JSONObject(request.getParameter("data"));
            return service.init(data);
        }catch (Exception e){
            object.put("status",500);
            object.put("error_msg","缺少参数");
        }
        return object.toString();
    }
}
