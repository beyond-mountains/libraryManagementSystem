<%@ page contentType="text/html;charset=UTF-8" import="javax.naming.*,com.example.sw.entity.*,java.util.*,java.net.*"
         language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<link href="./css/home.css" rel="stylesheet"/>
<script type="text/javascript">
    function search() {
        var type = document.getElementById("type").value;
        if (type == "author") {
            type = "findbookByAuthor";
        } else {
            type = "findbookByName";
        }
        var key = document.getElementById("key").value;
        window.location.href = "http://localhost:8080/sw/BookServlet?action=" + type + "&key=" + key;
    }
</script>
<body>

<form class="qw">
    <div id="left">图书馆</div>
    <div id="right">
        <input type="button" onclick='location.href=("center.jsp")' value="个人中心"/>
    </div>
</form>
<div class="child">
    <select id="type" style="width:120px;height:45px">
        <option value="name">书名</option>
        <option value="author">作者</option>

    </select>
    <input id="key" type="text" placeholder="搜索内容" style="width:300px;height:45px"/>
    <input type="button" onclick='search()' value="搜索" style="width:120px;height:45px"/>
</div>
<table border="2" align="center" border="1" style="font-size:30px;width:800px;height:30px">
    <tr>
        <td>书籍序号</td>
        <td>名称</td>
        <td>种类</td>
        <td>作者</td>
        <td>位置</td>
        <td>状态</td>
    </tr>
    <c:forEach items="${list }" var="entry" varStatus="id">
        <tr>
            <td>${entry.id }</td>
            <td>${entry.name }</td>
            <td>${entry.category }</td>
            <td>${entry.author }</td>
            <td>${entry.place }</td>
            <td>${entry.state }</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>