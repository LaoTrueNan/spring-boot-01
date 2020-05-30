$(function () {
    $(":input[name='username']").focus();
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
                return false;
            }
            var username= $(this).val();
            var url="/welcome/all?username="+username;
            $.get(url,function (data) {
                if(data!=null){
                    $("#message1").text(data);
                }
            })
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

    $("input[name='confirmPas']").blur(function () {
        if($("input[name='password']").val()!=$(this).val()){
            if($("#message1").text()=="" && $("#message2").text()=="") {
                $("#message3").text("两次输入不匹配！");
            }
        }
    });

    refocus($("input[name='confirmPas']"),$("#message3"));

    $("input[name='openid']").blur(function () {
       if($(this).val()==""){
           if($("#message1").text()=="" && $("#message2").text()=="" && $("#message3").text()==""){
               $("#message4").text("管理员ID不能为空！");
           }
       }else{
           var patrn = /^[0-9]{6,30}$/;
           if (!patrn.exec($(":input[name='openid']").val())){
               $("#message4").text("管理员ID不合输入规则！");
           }
           var openid= $(this).val();
           var url="/welcome/all?openid="+openid;
           $.get(url,function (data) {
               if(data!=null){
                   $("#message4").text(data);
               }
           })

       }
    });

    refocus($("input[name='openId']"),$("#message4"));

    $("button").bind('click',function(){
        if($("#message1").text()!=""||$("#message2").text()!=""||$("#message3").text()!=""||$("#message4").text()!=""){
            return false;
        }else{
            $(".myForm").submit();
        }
    });
});