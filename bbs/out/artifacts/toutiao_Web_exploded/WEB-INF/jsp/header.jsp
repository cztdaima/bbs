<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="header.css">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<ul>
  <li><a class="active" href="/">首页</a></li>

    <div class="dropdown">
        <a href="#" class="dropbtn">版块分区</a>
        <div class="dropdown-content">
            <a href="#">娱乐</a>
            <a href="#">新闻</a>
            <a href="#">知识</a>
        </div>
    </div>
    <li><a href="/post">发帖子</a></li>
    <li><a href="/inform">举报</a></li>
    <li><a href="#contact">关于</a></li>
  <c:if test="${empty user }">
        
	   <li style="float:right"><a id="login" href="${pageContext.request.contextPath}/login_page">登录</a></li>
	   
	   <li style="float:right"><a id="reg"  href="${pageContext.request.contextPath}/reg_page">注册</a></li>
   </c:if>
   <c:if test="${not empty user }">

       <li style="float:right"><a href="${pageContext.request.contextPath}/logout">退出</a></li>

		<li style="float:right"><a href="${pageContext.request.contextPath}/profile/${user.id}">欢迎您:${user.name},个人主页</a></li>

	</c:if>
</ul>
