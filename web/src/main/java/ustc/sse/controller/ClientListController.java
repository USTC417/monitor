package ustc.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.connection.MessageManager;
import ustc.sse.tools.dao.ClientDao;
import ustc.sse.tools.entity.ClientEntity;

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

    @RequestMapping("/clientClose")
    public String clientClose(HttpServletRequest request){
        String clientId = request.getParameter("clientId");
        //处理客户机关机操作
        MessageManager manager = new MessageManager();
        manager.sendMessage("close",clientId);
        return "basic_table.jsp";
    }

    @RequestMapping("/clientRestart")
    public String clientRestart(HttpServletRequest request){
        String clientId = request.getParameter("clientId");
        //处理客户机重启
        MessageManager manager = new MessageManager();
        manager.sendMessage("restart",clientId);
        return "basic_table.jsp";
    }

    @RequestMapping("/clientInfo")
    public String clientInfo(HttpServletRequest request ){
        String clientId = request.getParameter("clientId");
        //处理查询客户机信息
        ClientDao dao = new ClientDao();
        ClientEntity entity = dao.queryEntity(clientId);
        System.out.println("客户机信息:"+entity);
        return "specific_table.jsp";
    }
}
