<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link href="css/login.css" rel="stylesheet"/>

</head>
<script type="text/javascript">
    function login(){

    }
</script>
<body>
<div class="container">
    <div class="login-wrapper">
        <div class="header">登录</div>
        <div class="form-wrapper">
            <form method="post" action="LoginServlet">
                <input type="text" name="username" placeholder="用户名" class="input-item">
                <input type="password" name="password" placeholder="密码" class="input-item">
                <input type="hidden" name="action" value="Login">
                <input class="btn" type="submit" value="登录" />
            </form>
        </div>
    </div>
</div>
</body>
</html>
