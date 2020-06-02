$(function () {
    $("button[name='confirm']").bind('click',function () {
        var selected = $("select[name='id'] option:selected").text();
        if(selected=="未与超市绑定！"||selected==null){
            alert("必须选择一个超市!");
        }else{
            $("form[name='my']").submit();
        }
    })
})