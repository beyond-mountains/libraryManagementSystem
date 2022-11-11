package com.example.sw.control;

import com.example.sw.dao.BorrowDao;
import com.example.sw.entity.Borrow;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BorrowServlet", value = "/BorrowServlet")
public class BorrowServlet extends BaseServlet {
    private BorrowDao dao = new BorrowDao();
    public void getBorrowRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuid = request.getParameter("stuid");
        List<Borrow> list = dao.getBorrowByID(stuid);
        request.setAttribute("list", list);
        /* jsp读取方法 使用JSTL
            <c:forEach items="${list }" var="entry" varStatus="id">
    		<tr>
    			<td>${entry.属性名 }</td>
    			<td>${entry.属性名 }</td>
    			...
    		</tr>
    		</c:forEach>
         */
        request.setAttribute("return", "yes"); //标记返回 不需要可以删除
        request.getRequestDispatcher("").forward(request, response);//设置转发页面
    }
    public void delayDueDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String stu_id = request.getParameter("stuid");
        dao.updateDelayDate(id);
        List<Borrow> borrowList = dao.getBorrowByID(stu_id);
        request.getSession().setAttribute("borrowlist", borrowList);
        request.getRequestDispatcher("/center.jsp").forward(request, response);//设置转发页面
    }
}
