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
    <title>个人主页</title>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<jsp:include   page="header.jsp" flush="true"/>
<body style="text-align: center">
</br>

    <c:if test="${not empty user }">

        <img src=${user.head_url} class="round_icon"  alt="">

    </c:if>


        <c:if test="${ userId == user.id }">

            <a href="/changeHead" name="changeHeadUrl" id="button1">修改头像</a>
            <a href="/changePassword" name="changePassword" id="button2">修改密码</a>
        </c:if>


    <br />
    <br />
    <br />
    <br />
    <c:forEach var="vo" items="${vos }" >
        ${ vo.get("user").getName()}发布的贴子
    </c:forEach>
    <br />
    <br />
    <br />
    <br />
    <table style="width:100%; height:100%">
        <tr>
            <th></th>
            <th>title</th>
            <th style="width:20%">作者/时间</th>
            <th style="width:20%">评论数/点赞数</th>
            <c:if test="${ userId == user.id }">
                <th style="width: 10%">操作</th>
            </c:if>
        </tr>
        <c:forEach var="vo" items="${vos }" >
            <tr>
                <td class="image" style="width:6%"><img src="${ vo.get("news").getImage()}" height="auto" width="100%"></td>
                <td class ="title">
                    <p>【${ vo.get("news").getM_section()}】
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
                <c:if test="${ userId == user.id }">
                    <td style="width:10%">
                        <form action="/message_delete" method="delete">
                            <input type="hidden" name=user_id   value="${vo.get("user").getId()}">
                            <input type="hidden" name=message_id   value="${vo.get("news").getId()}">
                            <input id="like" type="submit" value="删除" name="submit">
                            <c:if test="${ code==0 }">
                                删除成功！
                            </c:if>
                            <c:if test="${ code==1 }">
                                后台服务出错啦！
                            </c:if>
                        </form>
                    </td>
                </c:if>

            </tr>
        </c:forEach>
    </table>

</body>

<jsp:include   page="footer.jsp" flush="true"/>
</html>
