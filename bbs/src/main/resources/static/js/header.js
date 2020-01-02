

    var loginUser = document.getElementById('loginUser').value;

    var obj = eval('(' + loginUser + ')');

     table = document.getElementById("li");

    if (loginUser !== "null") {
        s = '<li style="float:right"><a href="/logout">退出</a></li>' +
            '<li style="float:right"><a href="/profile/'+ obj.id + '">欢迎您: ' + obj.name +  ',个人主页</a></li>'


    }
    else {
        s = '<li style="float:right"><input id="button" type="button" onClick="openme()" value="登陆" /></li>' +
            '<li style="float:right"><a id="reg"  href="/reg_page">注册</a></li>'

    }


    $("#li").append(s);

    function openme(){

        document.getElementById('div2').style.display='block';
    }
    function closeme(){

        document.getElementById('div2').style.display='none';
    }
    function logo_in(){

        var userName = document.getElementById("userName").value;
        var passWord = document.getElementById("passWord").value;
        url = "http://localhost:8080/login?username=" + userName +"&password=" + passWord;
        if(userName.length < 5){
            alert("用户名不符合规范");
            return false;
        }
        if(passWord.length < 6){
            alert("密码不符合规范");
            return false;
        }
        closeme();

        $.ajax({

            type:"GET",
            url:"/login",

            data: 'username='+ userName +"&password="+ passWord,

            success:function(dataa) {

                var obj = eval('(' + dataa + ')');

                if(obj.code === 0){
                    url = "localhost:8080/admin";
                    location.href = "/admin";
                }
                else if(obj.code === 1){
                    location.href = "/";
                }
                else if(obj.code === 2){
                    alert("登录失败");

                }


            }
        });
    }