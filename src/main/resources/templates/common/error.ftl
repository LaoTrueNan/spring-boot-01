<html>
    <#include "header.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-warning">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>操作失败！
                </h4> <strong>${msg}</strong><br><a href="${url}" class="alert-link">即将自动跳转</a>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout('location.href="${url}"',3000);
</script>
</body>
</html>


