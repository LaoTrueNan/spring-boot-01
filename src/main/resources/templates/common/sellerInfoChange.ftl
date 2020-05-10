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
                <span id="info" hidden="hidden">${sellerInfo.id}</span>
                <form class="form-horizontal" role="form" action="/seller/confirmChange" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly="readonly" value="${sellerInfo.username}" name="username"/>
                            <span id="measage"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">管理员ID</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly="readonly" value="${sellerInfo.openid}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" id="opereation">${operation}</label>
                        <div class="col-sm-10">
                            <select name="id" class="form-control">
                                <#if (operation)?? && operation == "绑定超市"><option selected="selected" disabled="disabled">未与超市绑定！</option></#if>
                                <#list sellList as seller>
                                    <option value="${seller.id}" <#if (sellerInfo.id)?? && sellerInfo.id == seller.id>
                                            selected
                                    </#if>
                                    >${seller.id}-${seller.name}
                                    </option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">确认修改</button>
                        </div>
                    </div>

                </form>
            </div>
            <div class="col-md-4 column">

            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>