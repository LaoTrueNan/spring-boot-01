$(function () {

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

    $("input[name='username']").focus(function () {
        $("#message1").text("");
    });

    $("input[name='password']").blur(function () {
        if($("input[name='password']").val()==""){
            if($("#message1").text()=="") {
                $("#message2").text("密码不能为空！");
            }
        }
    });

    $("input[name='password']").focus(function () {
        $("#message2").text("");
    });

    $("button").bind('click',function(){
        if($("#message1").text()!=""||$("#message2").text()!=""){
            return false;
        }else{
            $(".form-horizontal").submit();
        }
    });

});