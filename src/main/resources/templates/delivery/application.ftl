<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>身份证号码 </th>
                            <th>手机号</th>
                            <th bgcolor="#CCCCCC">工作ID</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if deliveryPage.getTotalPages()==0>
                        <tr><td colspan="5">没有新的申请！（No applications!）</td></tr>
                        <#else>
                        <#list deliveryPage.content as deliverer>
                        <tr>
                            <td>${deliverer.name}</td>
                            <td>${deliverer.idNum}</td>
                            <td>${deliverer.phone}</td>
                            <td bgcolor="#CCCCCC">${deliverer.deliveryId}</td>
                            <td><a href="/delivery/hire?idNum=${deliverer.idNum}">聘为本超市配送员</a></td>
                        </tr>
                        </#list>

                        </#if>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage<2>
                        <li class="disabled">
                            <a>上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/delivery/list?page=${currentPage-1}&size=${size}">上一页</a>
                        </li>
                    </#if>
                    <#if deliveryPage.getTotalPages()==0>
                        <li hidden></li>
                    <#else>
                        <#list 1..deliveryPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li>
                                    <a href="/delivery/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>

                        </#list>
                    </#if>
                    <#if currentPage == deliveryPage.getTotalPages()||deliveryPage.getTotalPages()==0>
                        <li class="disabled">
                            <a>下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/delivery/list?page=${currentPage+1}&size=${size}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>