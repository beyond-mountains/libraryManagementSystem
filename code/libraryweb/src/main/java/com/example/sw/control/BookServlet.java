package com.example.sw.control;

import com.example.sw.dao.BookDao;
import com.example.sw.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Groza
 * @ClassName BookServlet
 * @Descreaption
 * @Date 17:55 2022/6/27
 */
@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet{
    private BookDao dao = new BookDao();
    public void findbookByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        List<Book> list = dao.getSampleByName(key);
        request.setAttribute("list", list); //列表放入attribute
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
        request.getRequestDispatcher("/home.jsp").forward(request, response);//设置转发页面
    }
    public void findbookByAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");
        List<Book> list = dao.getSampleByAuthor(key);
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
        request.setAttribute("return", "yes");
        request.getRequestDispatcher("/home.jsp").forward(request, response);//设置转发页面
    }
}
