
function like() {
    $.ajax({

        type: "GET",
        url: "/doChangePassword",

        data: "password=" + passWord,

        success: function (dataa) {

            var obj = eval('(' + dataa + ')');

            if (obj.code === "0") {
                alert("修改成功");
                window.location.reload();
            }
            else if (obj.code === "1") {
                alert("修改失败");

            }
            else if (obj.code === "2") {
                alert("后台出错了");

            }


        }
    });
}