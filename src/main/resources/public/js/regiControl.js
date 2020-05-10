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

    $("input[name='confirmPas']").blur(function () {
        if($("input[name='password']").val()!=$(this).val()){
            if($("#message1").text()=="" && $("#message2").text()=="") {
                $("#message3").text("两次输入不匹配！");
            }
        }
    });

    $("input[name='confirmPas']").focus(function () {
        $("#message3").text("");
    });

    $("input[name='openId']").blur(function () {
       if($(this).val()==""){
           if($("#message1").text()=="" && $("#message2").text()=="" && $("#message3").text()==""){
               $("#message4").text("管理员ID不能为空！");
           }
       }else{
           var patrn = /^[0-9]{6,30}$/;
           if (!patrn.exec($(":input[name='openId']").val())){
               $("#message4").text("管理员ID不合输入规则！");
           }
       }
    });

    $("input[name='openId']").focus(function () {
        $("#message4").text("");
    });

    $("button").bind('click',function(){
        if($("#message1").text()!=""||$("#message2").text()!=""||$("#message3").text()!=""||$("#message4").text()!=""){
            return false;
        }else{
            $(".myForm").submit();
        }
    });
});