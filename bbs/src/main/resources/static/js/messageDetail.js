
function like() {
    var message_id = document.getElementById('message_id').value;

    $.ajax({

        type: "GET",
        url: "/doLike",

        data: "messageId=" + message_id,

        success: function (dataa) {

            var obj = eval('(' + dataa + ')');

            if (obj.like === "0") {
                alert("点赞成功");
                window.location.reload();
            }
            else if (obj.like === "1") {
                alert("您已经点赞过了，请勿重复点赞");

            }
            else if (obj.like === "2") {
                alert("后台出错了");

            }


        }
    });
}

function validata(){
    var describe = document.getElementById('describe').value;
    var message_id = document.getElementById('message_id').value;

    if(describe.length ===0){
        alert("评论不能为空");
        return false;
    }

    $.ajax({

        type: "GET",
        url: "/message_comment",

        data: "message_id=" + message_id +"&describe=" + describe,

        success: function (dataa) {
            var obj = eval('(' + dataa + ')');
            if (obj.code === "0") {
                alert("评论成功");
                window.location.reload();
            }
            else if (obj.code === "1") {
                alert("评论内容不能为空");

            }
            else if (obj.code === "2") {
                alert("后台出错了");

            }
        },

    });

}


function inform() {
    var message_id = document.getElementById('message_id').value;

    $.ajax({

        type: "GET",
        url: "/inform",

        data: "message_id=" + message_id,

        success: function (dataa) {

            var obj = eval('(' + dataa + ')');

            if (obj.inform === "0") {
                alert("举报成功");

            }
            else if (obj.inform === "1") {
                alert("您已经举报过了，请勿重复举报");

            }
            else if (obj.inform === "2") {
                alert("后台出错了");

            }


        }
    });
}