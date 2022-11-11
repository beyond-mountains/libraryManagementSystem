<%@ page contentType="text/html;charset=UTF-8" import="javax.naming.*,com.example.sw.entity.*,java.util.*,java.net.*"
         language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<%
    String flag = "n";
    List<Borrow> list = (List<Borrow>) request.getSession().getAttribute("borrowlist");
    if (list != null)
        flag = "y";
%>
<link href="css/center.css" rel="stylesheet"/>
<script type="text/javascript">
    function update() {
        var type = "delayDueDate";
        var id = document.getElementById("id").value;
        var stuid = document.getElementById("stuid").value;
        window.location.href = "http://localhost:8080/sw/BorrowServlet?action=" + type + "&id=" + id +"&stuid=" + stuid;
    }
    window.onload = function init() {
        var flag = "<%=flag%>";
        if (flag === "y") {
            var div = document.getElementById("linename");
            div.style.display = "block";
        }
    }
</script>
<body>
<div class="warp">
    <div class="form">
        <div class="wacth">
             <div >
                <h3 >姓名:</h3>
                    <input class="input1" value="${sessionScope.user.name }" />
             </div>
             <div >
                <h3 >学号:</h3>
                    <input class="input1" id="stuid" value="${sessionScope.user.id }" />
             </div>
            <div>
                <h3>延长借阅的ID</h3>
                    <input class="input1" type = "text" id = "id">

            </div>
            <div class="but0">
                <input class="but1" type = "button" onclick='update()' value="提交">
            </div>
        </div>
    <div >
    借阅信息:
    </div>
<table border="2"align="center" border="1"  style="font-size:30px;width:800px;height:30px">
    <tr>
        <td>书籍编号</td>
        <td>借出时间</td>
        <td>截止时间</td>
        <td>归还时间</td>
        <td>ID</td>
    </tr>
    <c:forEach items="${sessionScope.borrowlist }" var="entry" varStatus="id">
        <tr>
            <td>${entry.bookid }</td>
            <td>${entry.lenddate }</td>
            <td>${entry.duedate }</td>
            <td>${entry.returndate }</td>
            <td>${entry.id }</td>
        </tr>
    </c:forEach>
</table>
    </div>
</div>

</body>
</html>