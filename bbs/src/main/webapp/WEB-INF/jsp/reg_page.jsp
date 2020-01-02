<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":" +request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>>">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>注册</title>

    <link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>
<div class="container">
    <!-- 导航 -->
    <jsp:include   page="header.jsp" flush="true"/>

    <!-- 主体内容 （登陆界面）-->
    <div class="main">
        <!-- 左侧信息栏 -->
        <div class="sideleft">
            <h1>东华大学    社交论坛</h1>
            <h4>社交娱乐  让生活更有趣</h4>
            </br></br></br>
            <p>多发帖，开心每一天。</p>
            <p>打造中国第一社交论坛。</p>

        </div>
        <!-- 右侧登陆界面 -->
        <div class="sideright">
            <div class="index">
                <form action="/reg" method="post" onSubmit="return validate()" name="loginForm">
                    <p class="headline">用户注册</p>
                    <p class="astyle">用户名：</p>
                    <input name="username" type="text" autocomplete="off">
                    <p class="astyle">密码：</p>
                    <input name="password" type="password">
                    <p class="astyle">重新输入您的密码：</p>
                    <input name="rePassword" type="password">
                    <input type="submit" value="注册">
                    </br>
                    <c:if test="${ code==0 }">
                    注册成功！
                    </c:if>
                    <c:if test="${ code==1 }">
                        后台服务出错啦！
                    </c:if>
                    <c:if test="${ code==2 }">
                        注册失败！
                    </c:if>
                    <c:forEach var="item" items="${coder }">
                        ${ item.key}  : ${item.value}<br>
                    </c:forEach>
                </form>
        <script>
            function validate()
            {

                if (document.loginForm.username.value.length<6)
                {
                    alert("账号长度不得小于6");

                    return false;
                }
                else if ( document.loginForm.password.value.length < 6)
                {
                    alert("密码长度不得小于6");

                    return false;
                }
                if (document.loginForm.password.value !== document.loginForm.rePassword.value)
                {
                    alert("两次密码不同");

                    return false;
                }

            }
        </script>

            </div>
        </div>
    </div>

    <!-- 尾部 -->
    <jsp:include   page="footer.jsp" flush="true"/>

</div>

</body>
</html>
