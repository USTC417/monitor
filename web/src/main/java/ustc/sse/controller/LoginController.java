package ustc.sse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ustc.sse.tools.dao.UserDao;
import ustc.sse.tools.entity.UserEntity;
import ustc.sse.tools.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 作者：陈志国
 * 时间：2017/11/26
 */
@Controller
public class LoginController {



   @RequestMapping("/LoginIn")
   @ResponseBody
    public String checkLogin(HttpServletRequest request, HttpSession session) throws Exception{
       String username = request.getParameter("username");
       String password = request.getParameter("password");

       UserDao dao = new UserDao();

       UserEntity user = dao.queryUserByNameandPwd(username,password);

       if (user!= null){
           session.setAttribute("user",user);
           return "1";
       }else{
           return "login.jsp";
       }

    }

    @RequestMapping("/LoginOut")
    public String loginOut(HttpSession session) throws Exception{
       session.removeAttribute("user");
       return "login.jsp";
    }

}
