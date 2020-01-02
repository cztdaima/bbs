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

    <title>登录</title>

    <link rel="stylesheet" type="text/css" href="login.css">

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
                <form action="/login" method="get">
                    <p class="headline">用户登陆</p>
                    <p class="astyle">用户名：</p>
                    <input type="text" name="username">
                    <p class="astyle">密码：</p>
                    <input type="text" name="password">
                    <input type="submit" value="登陆" name="login" >
                    </br>

                    <p class="cstyle">没有账号？<a href="/reg_page">立即注册</a></p>
                    <c:if test="${ code==0 }">
                        登录成功！
                    </c:if>
                    <c:if test="${ code==1 }">
                        后台服务出错啦！
                    </c:if>
                    <c:if test="${ code==2 }">
                        登录失败！
                    </c:if>
                    <c:forEach var="item" items="${coder }">
                        ${ item.key}  : ${item.value}<br>
                    </c:forEach>
                </form>

            </div>
        </div>
    </div>

    <!-- 尾部 -->
    <jsp:include   page="footer.jsp" flush="true"/>

</div>

</body>
</html>
