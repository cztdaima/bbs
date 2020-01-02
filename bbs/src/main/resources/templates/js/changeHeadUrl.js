window.onload = function() {
    var code = document.getElementById('code').value;
    if(code === "1"){
        alert("发布帖子失败，检查图片，标题和文章内容是否有误");
    }
    if(code === "2"){
        alert("后台出错了，请稍后再试");
    }
};