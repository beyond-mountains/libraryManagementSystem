<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>搜索结果</title>
</head>
<link href="search.css" rel="stylesheet"/>
<body>
<form>
    <div id="left">图书馆</div>
    <div  id="right">
        <input id="1" type="button" onclick='location.href=("center.html")' value="个人中心"    />
    </div>
</form>
<form id="sear">
    <select style="width:120px;height:45px">
        <option>作者</option>
        <option>书名</option>
    </select>
    <input type="text"  placeholder="搜索内容" style="width:300px;height:45px" />
    <input dir="rtl" type="button" onclick='location.href=("search.html")' value=搜索   style="width:120px;height:45px" />
</form>
<table border="2"align="center" border="1"  style="width:600px;height:200px">
    <tr>
        <td> 第一行第一列单元格</td>
    </tr>

    <tr>
        <td> 第二行第一列单元格</td>
    </tr>
    <tr>
        <td> 第三行第一列单元格</td>
    </tr>
</table>

</body>
</html>