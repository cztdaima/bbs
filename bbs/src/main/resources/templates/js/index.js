
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
    pop = document.getElementById("pop").value;
    if(pop==="1"){
    alert("请先登录再进行操作");
}
    else if(pop==="2"){
    alert("对不起您没有管理权限");
}
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


    function  sub(page){
    pop = document.getElementById("pop").value;

    if(pop==="1"){
    alert("请先登录,再进行操作");
}
    else if(pop==="2"){
    alert("对不起您没有管理员权限");
}
    document.getElementById("testid").innerText = page + 1;
    $.ajax({

    type:"GET",
    url:"/findLogPageMonth/" + page,


    success:function(dataa) {



    var obj = eval('(' + dataa + ')');
    console.log(dataa)
    var table=document.getElementById("table");

    for(var i=0,l=obj.length;i<l;i++){


    s  =  "<tr></tr>"+"<tr id=\"message\"><td class=\"image\" style=\"width: 6%\"><img src=" + obj[i].image + " height=\"auto\" width=\"100%\"></td><td class =\"title\" style='width: 34%'><a href=\"/title/"+ obj[i].id +  "\">"+ obj[i].title +"</a></td><td style=\"width:10%\"> <a href=\"/profile/"
    +obj[i].user_id + "\">"+ obj[i].userName+ "</a> <br/> <i class=\"date\">"+
    new Date(obj[i].created_date) + "</i></td> <td style=\"width:10%\">"+
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
