function changePassword(){

    var passWord = document.getElementById("newPassWord").value;
    var rePassWord = document.getElementById("rePassWord").value;
    // url = "http://localhost:8080/login?username=" + userName +"&password=" + passWord;


    if(passWord.length < 6 || rePassWord.length < 6){
        alert("密码不符合规范");
        return false;
    }
    if(passWord !== rePassWord){
        alert("两次密码不一样");
        return false;
    }

    closeChange();
    $.ajax({

        type:"GET",
        url:"/doChangePassword",

        data: "password="+ passWord,

        success:function(dataa) {

            var obj = eval('(' + dataa + ')');

            if(obj.code === "0"){
                alert("修改成功");
                window.location.reload();
            }
            else if(obj.code === "1"){
                alert("修改失败");

            }
            else if(obj.code === "2"){
                alert("后台出错了");

            }


        }
    });
}

function openChange(){

    document.getElementById('pswChange').style.display='block';
}
function closeChange(){

    document.getElementById('pswChange').style.display='none';
}






window.onload = function(){
    var userId = document.getElementById('userId').value;

    sub(userId);
};



function  sub(nowPage){



    $.ajax({


        type:"GET",
        url:"/profileDetail/" + nowPage,

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
                    obj[i].like_count+"</i></td>"+

                    "<span  th:if=\"${userId} eq ${user.id}\"> <td style=\"width:10%\"> <form action=\"/message_delete\" method=\"get\">"+

                        "<input type=\"hidden\" name=user_id   value=\""+ obj[i].user_id + "\">"+
                        "<input type=\"hidden\" name=message_id   value=\""+ obj[i].id +"\">"
                        + "<input id=\"like\" type=\"submit\" value=\"删除\" name=\"submit\"> </form> </td></span ></tr>";
                $("#table").append(s);


            }
        },
        error:function() {
            alert("数据请求失败==");
        }

    });

}

