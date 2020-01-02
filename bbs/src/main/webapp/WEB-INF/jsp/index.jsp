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
            pop = document.getElementById("pop").value;
            if(pop==="1"){
                alert("请先登录再进行操作");
            }
            else if(pop==="2"){
                alert("对不起您没有管理权限");
            }
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
            pop = document.getElementById("pop").value;

            if(pop==="1"){
                alert("请先登录,再进行操作");
            }
            else if(pop==="2"){
                alert("对不起您没有管理员权限");
            }
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
                            obj[i].like_count+"</i></td></tr>";
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

    <jsp:include   page="header.jsp" flush="true"/>
    <jsp:include   page="search.jsp" flush="true"/>

    <div style="text-align:center">
        <p style="text-align: center">
            总共有${pageSize}当前页数<span id="testid"></span>
            <input type="hidden" id="pop" value="${pop}">
            <input type="hidden" id="maxpage" value="${pageSize}">
            <input type="submit"  value="上一页" onclick="pre()">

            <input type="submit" value="下一页" onclick="next()">

        </p>
        <table  style="width:100%; height:100%" >
        <tr>

            <th style="width:60%">title</th>
            <th style="width:20%">作者/时间</th>
            <th style="width:20%">评论数/点赞数</th>
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

    <input type="hidden" id="pop" value="${pop}">
    <jsp:include   page="footer.jsp" flush="true"/>
</body>
</html>