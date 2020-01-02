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
    <link rel="stylesheet" type="text/css" href="css/post.css">
</head>
<body>
<jsp:include   page="header.jsp" flush="true"/>
<div class="Content-Main">
    <div class="Content-Main1">
        <h1>修改头像</h1>
    </div>
    <form action="/changePost" method="post" class="form-report" enctype="multipart/form-data">

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


                };

            </script>


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