package ustc.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ustc.sse.connection.ConnectorManager;

/** 启动位置
 * created by chenhanping
 * Desiner:chenhanping
 * Date:2017/11/20
 * Time:上午12:48
 */
@Controller
public class IndexController {

   @RequestMapping("/index")
    public ModelAndView index(){
        ConnectorManager manager = ConnectorManager.getInstance();
        return new ModelAndView("login.html");
    }
}
