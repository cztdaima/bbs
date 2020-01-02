<span style="font-size:18px;"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <!--
      <link rel="stylesheet" type="text/css" href="styles.css">
      -->
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


                     s  =  "<tr id=\"message\"><td class=\"image\"><img src=" + obj[i].image + " height=\"70\" width=\"70\"></td><td class =\"title\">【"+
                         obj[i].section +
                    "】<a href=\"/title/"+ obj[i].id +  "\">"+ obj[i].title +"</a></td><td style=\"width:20%\"> <a href=\"/profile/"


                    +obj[i].id + "\">"+ obj[i].userName+ "</a> <br/> <i class=\"date\">"+
                     new Date(obj[i].created_date) + "</i></td> <td style=\"width:20%\"><td>"+
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
      <link rel="stylesheet" type="text/css" href="css/index.css">
  </head>
  <br>



        <p style="text-align: center">
            总共有${pageSize}当前页数<span id="testid"></span>

        <input type="hidden" id="maxpage" value="${pageSize}">
        <input type="submit"  value="上一页" onclick="pre()">

        <input type="submit" value="下一页" onclick="next()">

       </p>


    <br>
 <div style="text-align:center">
       <table id="table">

    </table>


</div>



  </body>
</html>
