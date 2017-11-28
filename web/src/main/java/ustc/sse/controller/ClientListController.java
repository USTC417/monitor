package ustc.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.tools.dao.ClientDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 作者：陈志国
 * 时间：2017/11/27
 * 获取客户机列表的控制器
 */
@Controller
public class ClientListController {
    @RequestMapping("/queryClientList")
    public String queryClientList(HttpServletRequest request, HttpSession session)throws Exception{
        //获取客户端列表信息
        ClientDao dao = new ClientDao();
        //查询在线状态
        List clients = dao.queryClientList("1");
        session.setAttribute("clients",clients);

        return "basic_table.jsp";
    }
}
