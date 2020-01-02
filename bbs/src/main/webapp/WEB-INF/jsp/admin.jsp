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
    <title>管理员页面</title>
    <script type="text/javascript" src="js/background.js"></script>

    <link rel="stylesheet" type="text/css" href="css/index.css">
    <script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>


    <script type="text/javascript">
        var page=0;

        window.onload = function(){
            sub(page);
        };
        function pre() {

            if(page===0) {
                alert("已经是首页了");
                return false;
            }
            for(var i=0; i<10; i++){
                var box=document.getElementById("message");
                if(!box){
                    break;
                }
                box.parentNode.removeChild(box);
            }

            page -= 1;
            sub(page);
        }

        function next() {
            maxpage = document.getElementById("maxpage").value;

            if(maxpage-1===page){
                alert("已经是最后一页了");
                return false;
            }

            for(var i=0; i<10; i++){
                var box=document.getElementById("message");
                if(!box){
                    break;
                }
                box.parentNode.removeChild(box);
            }

            page += 1;
            sub(page)
        }


        function  sub(page){
            document.getElementById("testid").innerText = page + 1;
            $.ajax({

                type:"GET",
                url:"/findLogPageMonth/" + page,


                success:function(dataa) {



                    var obj = eval('(' + dataa + ')');
                    console.log(dataa)
                    var table=document.getElementById("table");

                    for(var i=0,l=obj.length;i<l;i++){


                        s  =  "<tr id=\"message\"><td class=\"image\" style=\"width: 6%\"><img src=" + obj[i].image + " height=\"auto\" width=\"100%\"></td><td class =\"title\" style='width: 44%'>【"+
                            obj[i].section +
                            "】<a href=\"/title/"+ obj[i].id +  "\">"+ obj[i].title +"</a></td><td style=\"width:20%\"> <a href=\"/profile/"
                            +obj[i].user_id + "\">"+ obj[i].userName+ "</a> <br/> <i class=\"date\">"+
                            new Date(obj[i].created_date) + "</i></td> <td style=\"width:20%\">"+
                            "<i class=\"comment\">评论数"+obj[i].comment_count+"</i><br/> <i class=\"like\">喜欢数"+
                            obj[i].like_count+"</i></td>" +
                            "<td style=\"width: 10%\"><form action=\"/admin_delete\" method=\"delete\">\n" +
                            "                            <input type=\"hidden\" name=message_id   value=\"" + obj[i].id + "\">\n" +
                            "                            <input id=\"like\" type=\"submit\" value=\"删除\" name=\"submit\">\n" +
                            "                            </td></tr>";
                        $("#table").append(s);

                    }
                },
                error:function() {
                    alert("数据请求失败==");
                }

            });

        }


    </script>
</head>
<body>
<c:if test="${empty user }">

    <li style="float:right"><a id="login" href="${pageContext.request.contextPath}/login_page">登录</a></li>

    <li style="float:right"><a id="reg"  href="${pageContext.request.contextPath}/reg_page">注册</a></li>
</c:if>
<c:if test="${not empty user }">

    <li style="float:right"><a href="${pageContext.request.contextPath}/logout">退出</a></li>

    <li style="float:right">你好管理员:${user.name}</li>

</c:if>
<h1 style="text-align: center">欢迎你管理员，你可以在本页面删除任意帖子</h1>

<div style="text-align:center">
    <p style="text-align: center">
        总共有${pageSize}当前页数<span id="testid"></span>

        <input type="hidden" id="maxpage" value="${pageSize}">
        <input type="submit"  value="上一页" onclick="pre()">

        <input type="submit" value="下一页" onclick="next()">

    </p>
    <table  style="width:100%; height:100%" >
        <tr>

            <th style="width:50%">title</th>
            <th style="width:20%">作者/时间</th>
            <th style="width:20%">评论数/点赞数</th>
            <th style="width:10%">操作</th>
        </tr>

        <div style="text-align:center">
            <table id="table" style="width:100%; height:100%">
            </table>


        </div>
    </table>
    <p style="text-align: center">


        <input type="hidden" id="maxpage" value="${pageSize}">
        <input type="submit"  value="上一页" onclick="pre()">

        <input type="submit" value="下一页" onclick="next()">


    </p>

</div>

</body>
</html>