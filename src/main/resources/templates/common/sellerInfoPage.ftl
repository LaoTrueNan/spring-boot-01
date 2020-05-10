<head>
    <meta charset="utf-8">
    <title>后端管理</title>
    <link href="/css/style.css" rel="stylesheet" >
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="row clearfix">
            <div class="col-md-2 column">
            </div>
            <div class="col-md-6 column">
                <img alt="图片无法显示" src="${(seller.avatar)!''}" class="img-thumbnail" width="300" height="300"/>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">超市名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly="readonly" value="${seller.name}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">超市地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly="readonly" value="${seller.address}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">管理员ID</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly="readonly" value="${sellerInfo.openid}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">操作</label>
                        <div class="col-sm-10">
                            <span><a href="/seller/changeInfo?username=${sellerInfo.username}&isBind=${isBind}">修改信息</a></span>
                        </div>
                    </div>
                </form>
            </div>
        <div class="col-md-4 column">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        超市公告
                    </h3>
                </div>
                <div class="panel-body">
                    ${seller.bulletin}
                </div>
                <div class="panel-footer">
                    ${(seller.name)!''}
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
</div>
</body>
</html>