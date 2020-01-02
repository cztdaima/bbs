
var code = document.getElementById('code');
alert(code);
function imgChange(obj) {

    //获取点击的文本框

    var file = document.getElementById("file");

    var imgUrl = window.URL.createObjectURL(file.files[0]);

    var img = document.getElementById('imghead');

    img.setAttribute('src',imgUrl); // 修改img标签src属性值

};

