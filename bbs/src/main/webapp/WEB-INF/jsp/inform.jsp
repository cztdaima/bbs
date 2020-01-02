<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":" +request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>>">


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>举报</title>
    <script  src="js/background.js"></script>
    <link rel="stylesheet" type="text/css" href="css/inform.css">
</head>
<body>
<jsp:include   page="header.jsp" flush="true"/>
<div class="Content-Main">
    <div class="Content-Main1">
        <h1>举报</h1>
    </div>
    <form action="/inform_page" method="post" class="form-report">
        <label>
            <span>Type:</span>
            <select name="select2" class="select2">
                <option value="涉及诈骗">涉及诈骗</option>
                <option value="涉及侵权">涉及侵权</option>
                <option value="虚假内容">虚假内容</option>
                <option value="垃圾信息">垃圾信息</option>
                <option value="色情信息">色情信息</option>
            </select>
        </label>
        <label>
            <span>URL:</span>
            <textarea id="url" name="url" placeholder="请在此填写网站，需以http或https开头"></textarea>
        </label>
        <label>
            <span>Describe:</span>
            <textarea id="describe" name="describe" placeholder="详细填写举报理由，有利于审核，不得少于8个字"></textarea>
        </label>

        <label>
            <span>Email:</span>
            <textarea id="email" name="email" placeholder="请务必填写正确的邮箱地址"></textarea>
        </label>
        <label>
        <input type="submit" value="提交" name="submit" >
        </label>
        <c:if test="${ code==0 }">
            提交成功！
        </c:if>
        <c:if test="${ code==1 }">
            后台服务出错啦！
        </c:if>
    </form>
</div>
<jsp:include   page="footer.jsp" flush="true"/>
</body>
</html>