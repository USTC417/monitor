package ustc.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ustc.sse.connection.ConnectorManager;

import javax.annotation.Resource;

/**
 * created by chenhanping
 * Designer:chenhanping
 * Date:2017/11/20
 * Time:上午12:48
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ConnectorManager manager = ConnectorManager.getInstance();
        return new ModelAndView("index.jsp");
    }
}
