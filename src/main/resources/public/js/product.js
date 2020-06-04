$(function () {
    $("tr td:nth-child(10) a").bind('click',function () {
        var op=$(this).text();
        var msg="是否确认"+op;
        var url = $(this).attr('href');
        if(confirm(msg)==true){
            $.get(url,function (data) {
                if(data=="/seller/product/list"){
                    alert(op+"成功");
                    location.href=data;
                }else {
                    alert(data);
                }
            })
        }else{
            return false;
        }
        return false;
    })

    $(":input[name='username']").focus();
    function refocus(e1,e2) {
        e1.focus(function () {
            e2.text("");
        })
    }

    $("input[name='productName']").blur(function () {
        if($("input[name='productName']").val()==""){
            $("#message1").text("商品名不能为空！");
        }
    });

    refocus($("input[name='productName']"),$("#message1"));

    $("input[name='productPrice']").blur(function () {
        if($("input[name='productPrice']").val()==""){
            if($("#message1").text()=="") {
                $("#message2").text("请填写价格！");
            }
        }else{
            var patrn = /^\d+(\.\d+)?$/;
            if (!patrn.exec($(":input[name='productPrice']").val())){
                if($("#message1").text()==""){
                    $("#message2").text("请填写合法的价格！");
                }
                }
            }
    });

    refocus($("input[name='productPrice']"),$("#message2"));

    $("input[name='productStock']").blur(function () {
        if($("input[name='productStock']").val()==""){
            if($("#message1").text()=="" && $("#message2").text()=="") {
                $("#message3").text("请填写商品库存！");
            }
        }
    });

    refocus($("input[name='productStock']"),$("#message3"));

    $("input[name='productIcon']").blur(function () {
        if($(this).val()==""){
            if($("#message1").text()=="" && $("#message2").text()=="" && $("#message3").text()==""){
                $("#message4").text("图片不能为空！");
            }
        }
    });

    refocus($("input[name='productIcon']"),$("#message4"));

    $("button").bind('click',function(){
        if($("#message1").text()!=""||$("#message2").text()!=""||$("#message3").text()!=""||$("#message4").text()!=""){
            return false;
        }else{
            var data = $("form.myForm").serialize();
            var targeturl = $("form.myForm").attr("action");
            $.ajax({
                async:false,
                type:'post',
                url:targeturl,
                data:data,
                datatype:'json',
                success:function (data) {
                    if(data=="/seller/product/list"){
                        alert("操作成功！");
                        location.href=data;
                    }else{
                        alert(data);
                    }
                }
            })
        }
    });
})