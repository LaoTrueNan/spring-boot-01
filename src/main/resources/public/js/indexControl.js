$(function () {

    function refocus(e1,e2) {
        e1.focus(function () {
            e2.text("");
        })
    }
    $("input[name='username']").blur(function () {
        if($("input[name='username']").val()==""){
            $("#message1").text("用户名不能为空！");
        }else{
                var patrn = /^[a-zA-Z]{1,30}$/;
                if (!patrn.exec($(":input[name='username']").val())){
                    $("#message1").text("用户名不合输入规则！");
                }
            }
    });

    refocus($("input[name='username']"),$("#message1"));

    $("input[name='password']").blur(function () {
        if($("input[name='password']").val()==""){
            if($("#message1").text()=="") {
                $("#message2").text("密码不能为空！");
            }
        }
    });

    refocus($("input[name='password']"),$("#message2"));

    $("button").bind('click',function(){
        if($("#message1").text()!=""||$("#message2").text()!=""){
            return false;
        }else{
            $(".form-horizontal").submit();
        }
    });

});