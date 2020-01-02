
    window.onload = function() {
        var code = document.getElementById('code').value;

        if(code === "0"){
            alert("发布帖子成功");
        }
        if(code === "1"){
            alert("发布帖子失败，检查图片，标题和文章内容是否有误");
        }
        if(code === "2"){
            alert("后台出错了，请稍后再试");
        }
    };
    function imgChange(obj) {

    //获取点击的文本框

    var file = document.getElementById("file");

    var imgUrl = window.URL.createObjectURL(file.files[0]);

    var img = document.getElementById('imghead');

    img.setAttribute('src',imgUrl); // 修改img标签src属性值

};

