package com.example.sw.control;

import com.example.sw.dao.BorrowDao;
import com.example.sw.dao.StudentDao;
import com.example.sw.entity.Borrow;
import com.example.sw.entity.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends BaseServlet {
    private StudentDao dao = new StudentDao();
    private BorrowDao dao_bor = new BorrowDao();
    public void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("进入login方法");
        String account = request.getParameter("username");
        String password = request.getParameter("password");
        Student stu = null;
        //System.out.println(account);
        //dao.test();
        if((stu = dao.findUserWithAccountAndPassword(account, password)) != null) {
            //System.out.println("找到对象了");
            request.getSession().setAttribute("user", stu); //将Student实体放入Session中保持登录状态
            List<Borrow> borrowList = dao_bor.getBorrowByID(stu.getId());
            if(borrowList!=null) {
                request.getSession().setAttribute("borrowlist", borrowList);
            }
            response.sendRedirect("/sw/home.jsp");//设置登录成功转发
        } else {
            response.sendRedirect("");//设置登录失败转发
        }
    }
}
