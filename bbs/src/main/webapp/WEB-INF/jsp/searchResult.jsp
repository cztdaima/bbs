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
    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="css/index.css">

</head>
<body>
<jsp:include   page="header.jsp" flush="true"/>
<jsp:include   page="search.jsp" flush="true"/>
<div style="text-align:center">
    <table style="width:100%; height:100%">
        <tr>
            <th></th>
            <th>title</th>
            <th style="width:20%">作者/时间</th>
            <th style="width:20%">评论数/点赞数</th>
        </tr>
        <c:forEach var="vo" items="${vos }" >
            <tr>
                <td class="image"><img src="${ vo.get("news").getImage()}" height="auto" width="100%"></td>
                <td class ="title">
                    <p>【${ vo.get("news").getSection()}】
                        <a href="/title/${vo.get("news").getId()}">${ vo.get("news").getTitle()}</a>
                    </p>
                </td>
                <td style="width:20%">
                    <a href="/profile/${ vo.get("user").getId()}">${ vo.get("user").getName()}</a>
                    <br/>
                    <i class="date">${ vo.get("news").getCreated_date()}</i></td>
                <td style="width:20%">
                    <i class="comment">评论数${ vo.get("news").getComment_count()}</i>
                    <br/>
                    <i class="like">喜欢数${ vo.get("news").getLike_count()}</i>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>


<jsp:include   page="footer.jsp" flush="true"/>
</body>
</html>