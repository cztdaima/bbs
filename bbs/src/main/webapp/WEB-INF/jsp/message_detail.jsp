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
    <title>${ vo.get("news").getTitle()}</title>
    <link rel="stylesheet" type="text/css" href="css/inform.css">
</head>
<body>
<jsp:include   page="header.jsp" flush="true"/>
<div style="text-align:center">
    <div style="border: 1px solid black;border-radius: 15px;box-shadow: 10px 10px 5px #888888;">
        <div style="border: 1px solid black;border-radius: 10px;whith:100%;height: 50px;background-color:#9DB3C5">
            <p>【${ vo.get("news").getSection()}】
                <a href="/title/${vo.get("news").getId()}">${ vo.get("news").getTitle()}</a>
                <i style="float: right">
                    <i class="like">喜欢数&nbsp;${ vo.get("news").getLike_count()}</i>
                    <i class="comment">评论数${ vo.get("news").getComment_count()}&nbsp;</i>
                </i>
            </p>
        </div>
        <table style="width:100%">
            <tr>
                <td style="whith:20%;background: #E8F3FD;border-radius: 15px">
                    <div style="border: 0px solid black;height:500px;float:left">
                        个人id： ${vo.get("user").getId()} &nbsp; &nbsp; &nbsp;
                        </br>
                        个人姓名: ${vo.get("user").getName()}
                        </br>
                        <img src=${vo.get("user").getHead_url()} height="150" width="150">
                    </div>
                </td>
                <td style="width:80%">
                    <div>
                        <table style="height:300px;width: 100%">
                            <tr>
                                <td style="width: 20%;">
                            <span><img src=${ vo.get("news").getImage()} height="auto" width="100%">
                            </span>
                                </td>
                                <td style="width: 60%;">
                                    <div style="border: 1px solid white; height: 300px;float:left;overflow:hidden;width: 100%;text-align: left">
                                        <i class="content"><br/>${ vo.get("news").getContent()}<br/></i>
                                    </div>
                                </td>
                            </tr>

                        </table>
                    </div>


                    <div style="border-top: 1px solid black; height: 200px;clear: both;">
                        <div>
                            <form action="/message_comment" method="post">
                                <input type="hidden" name=message_id   value="${vo.get("news").getId()}">
                                <textarea id="describe" name="describe" placeholder="填写回复内容" style="width: 400px"></textarea>
                                <input id="post" type="submit" value="快速回复" name="submit">
                                <input id="like" type="submit" value="点赞" name="submit">
                                <c:if test="${ code==0 }">
                                    提交成功！
                                </c:if>
                                <c:if test="${ code==1 }">
                                    后台服务出错啦！
                                </c:if>
                            </form>
                        </div>
                        </br>
                        <i class="date">创建日期&nbsp;${ vo.get("news").getCreated_date()}</i>
                    </div>
                    <div style="clear: both;"></div>

                </td>
            </tr>

        </table>
</div>


</br>
    <c:forEach var="co" items="${cos }" >
    </br>
    <div style="border: 1px solid black;width: 80%;margin: 0 auto;border-radius: 8px;box-shadow: 10px 10px 5px #888888;">
        <table style="width: 100%">
            <tr>
                <td style="width:20%;background: #E8F3FD">
                    <div style="border: 0px solid black;height:300px;float:left">
                        回帖id： ${co.get("comment").getId()} &nbsp; &nbsp; &nbsp;
                        </br>
                        回帖人id： ${co.get("comment").getUser_id()} &nbsp; &nbsp; &nbsp;
                        </br>
                        <img src=${co.get("user")} height="150" width="150">
                        </br>
                            ${co.get("comment").getCreate_date()}
                    </div>
                </td>
                <td style="width: 70%">
                    <div style="border: 2px solid white;height:300px;float:left">
                        <p>
                                ${ co.get("comment").getComment()}
                        </p>

                    </div>
                </td>
            </tr>
        </table>
        <div style="clear: both;"></div>
    </div>
    </c:forEach>
    <div class="Content-Main">
        <div class="Content-Main1">
            <h1>回复</h1>
        </div>
        <form action="/message_comment" method="post" class="form-report">
            <label>
                <span>回复内容:</span>
                <input type="hidden" name=message_id   value="${vo.get("news").getId()}">
                <textarea id="describe2" name="describe" placeholder="填写回复内容，不得少于8个字"></textarea>
            </label>
            <l>
                <input type="submit" value="快速回复" name="submit" >
            </label>
            <c:if test="${ code==0 }">
                提交成功！
            </c:if>
            <c:if test="${ code==1 }">
                后台服务出错啦！
            </c:if>
        </form>
    </div>
</div>


<jsp:include   page="footer.jsp" flush="true"/>
</body>
</html>