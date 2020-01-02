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
    <title>发布帖子</title>
    <style type="text/css">
        .Content-Main{
            max-width: 500px;
            margin: auto;
            margin-top: 50px;
            padding: 20px 30px 20px 30px;
            font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;
            text-shadow: 1px 1px 1px #FFF;
            border: 1px solid #DDD;
            border-radius: 5px;
            color: #888;
            background: #FFF;
        }
        .Content-Main h1{
            display: block;
            padding: 0px 0px 10px 40px;
            margin: -10px -30px 30px -30px;
            font: 25px "Helvetica Neue", Helvetica, Arial, sans-serif;
            border-bottom: 1px solid #DADADA;
            color: #888;
        }
        .Content-Main h1>span{
            display: block;
            font-size: 11px;
        }
        .Content-Main label{
            display: block;
            margin: 0px 0px 5px;
        }
        .Content-Main label>span{
            float: left;
            width: 20%;
            padding-right: 10px;
            margin-top: 10px;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-weight: bold;
            text-align: right;
            color: #333;
        }
        .Content-Main input[type="text"],.Content-Main textarea{
            width: 70%;
            height: 20px;
            padding: 5px 0px 5px 5px;
            margin-bottom: 16px;
            margin-right: 6px;
            margin-top: 2px;
            line-height: 15px;
            border-radius: 4px;
            border: 1px solid #CCC;
            color: #888;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        }
        .select1{
            width: 71%;
            height: 35px;
            margin-bottom: 16px;
            margin-right: 6px;
            margin-top: 2px;
            line-height: 15px;
            padding: 5px 0px 5px 5px;
            border-radius: 4px;
            border: 1px solid #CCC;
            color: #888;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        }
        .select2{
            width: 25%;
            height: auto;
            padding-top: 1px;
            padding-bottom: 3px;
            padding-left: 1px;
            margin-top: 5px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #CCC;
            color: #888;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
        }
        .Content-Main textarea{
            width: 70%;
            height: 100px;
            padding: 5px 0px 0px 5px;
        }
        #url{
            width: 70%;
            height: auto;
            padding: 5px 0px 25px 5px;
        }
        #describe{
            width: 70%;
            height: auto;
            padding: 5px 0px 80px 5px;
        }
        #title{
            width: 70%;
            height: auto;
            padding: 5px 0px 5px 5px;
        }
        #verifiation code{
            width: 20px;
            height: auto;
        }
        #button{
            padding: 10px 25px 10px 25px;
            margin-left: 111px;
            border-radius: 4px;
            border:1px solid #CCC;
            background: #FFF;
            color: #333;
        }
        #button:hover{
            color: #333;
            background-color: #EBEBEB;
            border-color: #ADADAD;
        }
    </style>
</head>
<body>
<jsp:include   page="header.jsp" flush="true"/>
<div class="Content-Main">
    <div class="Content-Main1">
        <h1>发布帖子</h1>
    </div>
    <form action="/post_page" method="post" class="form-report" enctype="multipart/form-data">
        <label>
            <span>Type:</span>
            <select name="select2" class="select2">
                <option value="1">娱乐</option>
                <option value="2">新闻</option>
                <option value="3">知识</option>

            </select>
        </label>
        <label>
            <div class="upload_box" id="url">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <b>&nbsp;&nbsp;&nbsp;上传图片</b>

            <input type="file" name="file" id="file" accept="image/*" onchange="imgChange(this);"/> <!--文件上传选择按钮-->

            <div id="preview">

            <img id="imghead"  src="insert_image.gif" width="260" height="180" /> <!--图片显示位置-->

</div>

</div>

        <script type="text/javascript">

        // 选择图片显示

        function imgChange(obj) {

        //获取点击的文本框

        var file =document.getElementById("file");

        var imgUrl =window.URL.createObjectURL(file.files[0]);

        var img =document.getElementById('imghead');

        img.setAttribute('src',imgUrl); // 修改img标签src属性值

        };

        </script>
            <%--<span>URL:</span>--%>
            <%--<textarea id="url" name="url" placeholder="请在此填写图片url，否则无法正常显示"></textarea>--%>
        </label>
        <label>
            <span>title:</span>
            <textarea id="title" name="title" placeholder="请填写写您的帖子title"></textarea>
        </label>
        <label>
            <span>Describe:</span>
            <textarea id="describe" name="describe" placeholder="请填写帖子内容"></textarea>
        </label>


        <label>
            <input id="button" type="submit" value="提交" name="submit" >
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