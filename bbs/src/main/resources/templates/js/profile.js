function changePassword(){

    var rePassWord = document.getElementById("rePassWord").value;
    var passWord = document.getElementById("passWord").value;
    url = "http://localhost:8080/login?username=" + userName +"&password=" + passWord;

    if(passWord.length < 6 || rePassWord.length < 6){
        alert("密码不符合规范");
        return false;
    }
    if(passWord !== rePassWord){
        alert("两次密码不一样");
    }
    closeme();

    $.ajax({

        type:"GET",
        url:"/doChangePassword",

        data: "password="+ passWord,

        success:function(dataa) {

            var obj = eval('(' + dataa + ')');

            if(obj.code === 0){
                alert("修改成功");
                window.location.reload();
            }
            else if(obj.code === 1){
                alert("修改失败");

            }
            else if(obj.code === 2){
                alert("后台出错了");

            }


        }
    });
}

function openMe(){

    document.getElementById('div2').style.display='block';
}
function closeMe(){

    document.getElementById('div2').style.display='none';
}