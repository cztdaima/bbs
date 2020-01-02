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

    <title>介绍</title>

    <link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>

    <!-- 导航 -->
    <jsp:include   page="header.jsp" flush="true"/>

    <!-- 主体内容 （登陆界面）-->

        <!-- 左侧信息栏 -->
        <br/>
        <br/>
        <br/>
        <div style="text-align:center">
            <h1>东华大学    社交论坛</h1>
            <br/>
            <br/>
            <br/>
            <h4>社交娱乐  让生活更有趣</h4>
            </br></br></br>
            <p>多发帖，开心每一天。</p>
            <p>打造中国第一社交论坛。</p>
            <br/>
            <br/>
            <br/>
        </div>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <!-- 尾部 -->
    <jsp:include   page="footer.jsp" flush="true"/>


</body>
</html>
