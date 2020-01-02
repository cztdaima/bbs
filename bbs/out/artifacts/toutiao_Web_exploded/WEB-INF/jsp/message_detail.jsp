<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <title>信息详情页</title>

</head>
<body >
<jsp:include   page="header.jsp" flush="true"/>
<div style="text-align:center">
        个人id： ${vo.get("user").getId()} &nbsp; &nbsp; &nbsp;
        个人姓名: ${vo.get("user").getName()}
        <br />

        <br/>
        <img src=${ vo.get("news").getImage()} height="140" width="140">
        <br/>
        <span>
                <i class="comment">评论数&nbsp;${ vo.get("news").getComment_count()}</i>
            </span>
        <span>
                <i class="like">喜欢数&nbsp;${ vo.get("news").getLike_count()}</i>
            </span>
        <br/>
        <span>
                <i class="date">创建日期&nbsp;${ vo.get("news").getCreated_date()}</i>
            </span>
        <br/>


</div>


<jsp:include   page="footer.jsp" flush="true"/>
</body>
</html>