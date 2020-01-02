
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

function jump() {
    maxpage = parseInt(document.getElementById("maxpage").value);
    jumpPage = parseInt(document.getElementById("jumpPage").value);

    if(jumpPage > 0 &&  jumpPage<=maxpage){
        for(var i=0; i<10; i++){
            var box=document.getElementById("message");
            if(!box){
                break;
            }
            box.parentNode.removeChild(box);
        }
        page = jumpPage-1;
        sub(jumpPage-1);

    }
    else{
        alert("输入数据失败，请重新输入");
        return false;
    }
}


function  sub(nowPage){
    var maxpage = document.getElementById("maxpage").value;

    document.getElementById("testid").innerText = nowPage + 1;
    $.ajax({


        type:"GET",
        url:"/findInform/" + nowPage,
        data: 'maxPage='+ maxpage,

        success:function(dataa) {



            var obj = eval('(' + dataa + ')');
            var table=document.getElementById("table");




            for(var i=0,l=obj.length;i<l;i++){
                console.log(obj[i]);

                s  =  "<tr id=\"message\"><td class=\"image\" style=\"width: 6%\"><img src=" + obj[i].messageImage +
                    " height=\"auto\" width=\"100%\"></td><td class =\"title\" style='width: 44%'>"+
                    "<a href=\"/title/"+ obj[i].messageTitleId +  "\">"+ obj[i].messageTitle +"</a></td><td style=\"width:20%\"> <a href=\"/profile/"
                    +obj[i].messageUserId + "\">"+ obj[i].messageUserName+ "</a> <br/> <i class=\"date\">"+
                      "</i></td> <td style=\"width:20%\">"
                    + "<a href=\"/title/"+ obj[i].informUserId +  "\">"+ obj[i].informUserName +"</a> "+
                    "</td>" +
                    "<td style=\"width: 10%\"><form action=\"/adminDeleteInform\" method=\"post\">" +
                    "<input type=\"hidden\" name=message_id   value=\"" + obj[i].messageTitleId + "\">" +
                    "<input type=\"hidden\" name=inform_id   value=\"" + obj[i].informId + "\">" +
                    "<input id=\"like\" type=\"submit\" value=\"删除\" name=\"submit\">" +
                    "</td></tr>";
                $("#table").append(s);

            }
        },
        error:function() {
            alert("数据请求失败==");
        }

    });

}

