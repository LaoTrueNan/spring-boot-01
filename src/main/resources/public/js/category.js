$(function () {
    function refocus(e1,e2) {
        e1.focus(function () {
            e2.text("");
        })
    }
    $(":input[name='categoryName']").blur(function () {
        if($(this).val()==""){
            $("#message1").text("类目名称不能为空");
        }
    })

    refocus($(":input[name='categoryName']"),$("#message1"));

    $(":input[name='categoryType']").blur(function () {
        if($(this).val()==""){
            if($("#message1").text()==""){
                $("#message2").text("type不能为空");
            }
        }
    })

    $("button").bind('click',function () {
        if($("#message1").text()!=""||$("#message2").text()!=""){
            return false;
        }else{
            var data = $("form[role='form']").serialize();
            var targeturl = $("form[role='form']").attr("action");
            $.ajax({
                async:false,
                type:'post',
                url:targeturl,
                data:data,
                datatype:'json',
                success:function (data) {
                    if(data=="/seller/category/list"){
                        alert("操作成功！");
                        location.href=data;
                    }else{
                        alert(data);
                    }
                }
            })
        }
    })

    refocus($(":input[name='categoryType']"),$("#message2"));

    //是否确认删除
    $("tr td:nth-child(5) a").bind('click',function () {
        var msg="是否确认"+$(this).text();
        var url = $(this).attr('href');
        if(confirm(msg)==true){
            $.get(url,function (data) {
                if(data=="/seller/category/list"){
                    alert("删除成功！");
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
})