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

    <title>招聘信息</title>

    <link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>

    <!-- 导航 -->
    <jsp:include   page="header.jsp" flush="true"/>

    <!-- 主体内容 （登陆界面）-->

    <!-- 左侧信息栏 -->
    <div style="text-align:center">
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>
        <h1>请联系我们组长蒋泽宇，他有钱</h1>


    </div>

    <!-- 尾部 -->
    <jsp:include   page="footer.jsp" flush="true"/>


</body>
</html>
