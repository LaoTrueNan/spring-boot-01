$(function () {
    $("button[name='confirm']").bind('click',function () {
        var selected = $("select[name='id'] option:selected").text();
        if(selected=="未与超市绑定！"||selected==null){
            alert("必须选择一个超市!");
        }else{
            var targeturl = $("form[name='my']").attr('action');
            var data = $("form[name='my']").serialize();
            $.ajax({
                async:false,
                type:'post',
                url:targeturl,
                data:data,
                datatype:'json',
                success:function (data) {
                    if(data=="success"){
                        alert("操作成功！");
                        location.href="/common/sellerinfopage.html";
                    }else{
                        alert("ERROR！");
                    }
                }
            })
        }
    })
})