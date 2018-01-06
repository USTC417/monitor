package ustc.sse.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.connection.MessageManager;
import ustc.sse.tools.dao.ClientDao;
import ustc.sse.tools.entity.ClientEntity;
import ustc.sse.tools.util.Util;

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
        JSONObject object = new JSONObject();
        object.put("cmd","close");
        object.put("client_id",clientId);
        object.put("cmd_id", Util.createId());
        manager.sendMessage(object,clientId);
        //return new ModelAndView("basic_table.jsp");
        return "redirect:queryClientList";
    }

    @RequestMapping("/clientRestart")
    public String clientRestart(HttpServletRequest request){
        String clientId = request.getParameter("clientId");
        //处理客户机重启
        MessageManager manager = new MessageManager();
        JSONObject object = new JSONObject();
        object.put("cmd","restart");
        object.put("client_id",clientId);
        object.put("cmd_id", Util.createId());
        manager.sendMessage(object,clientId);
        return "redirect:queryClientList";
    }

   /* @RequestMapping("/clientInfo")
    public ModelAndView clientInfo(HttpServletRequest request ){
        String clientId = request.getParameter("clientId");
        //处理查询客户机信息
        ClientDao dao = new ClientDao();
        ClientEntity entity = dao.queryEntity(clientId);
        System.out.println("客户机信息:"+entity);
        ModelAndView view = new ModelAndView( "specific_table.jsp");
        view.addObject("clientId",entity.getClientId());
        view.addObject("clientName",entity.getClientName());
        view.addObject("clientIp",entity.getClientCpu());
        view.addObject("clientCpu",entity.getClientCpu());
        view.addObject("clientStorage",entity.getClientStorage());
        view.addObject("clientRam",entity.getClientRam());
        view.addObject("clientLogPath",entity.getClientLogPath());

        return view;
    }*/

    /**
     * 查询客户机详细信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/clientInfo")
    public String clientInfo(Model model,HttpServletRequest request ){
        String clientId = request.getParameter("clientId");
        //处理查询客户机信息
        ClientDao dao = new ClientDao();
        ClientEntity entity = dao.queryEntity(clientId);
        System.out.println("客户机信息:"+entity);
        model.addAttribute("client",entity);

        return "specific_table.jsp";
    }

    @RequestMapping("/updateClientInfo")
    public String updateClientInfo(Model model,HttpServletRequest request){
        String clientId = request.getParameter("clientId");
        String clientName = request.getParameter("clientName");
        String clientIp = request.getParameter("clientIp");
        System.out.println("clientId"+clientId);
        System.out.println(clientName);
        System.out.println(clientIp);
        ClientDao dao = new ClientDao();
        int result = dao.updateClientInfoByclientId(clientName,clientIp,clientId);
        System.out.println("结果:"+result);

        ClientEntity entity = dao.queryEntity(clientId);
        System.out.println("客户机信息:"+entity);
        model.addAttribute("client",entity);
    //    model.addAttribute("result",result);
        return "specific_table.jsp";
      //  return "clientInfo";
    }

}
