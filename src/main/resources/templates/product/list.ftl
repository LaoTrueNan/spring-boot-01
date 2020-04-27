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
                                <th>商品id</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价 </th>
                                <th>库存</th>
                                <#--<th>描述</th>-->
                                <td>状态</td>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img height="100" width="100" src="${productInfo.productIcon}" alt="图片无法显示" </td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.getProductStatusEnum().message}</td>
                                <#--<td>${productInfo.productDescription}</td>-->
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>

                                <td><#if productInfo.productStatus==0><a href="/sell/seller/product/offsale?productId=${productInfo.productId}">下架</a>
                                <#else><a href="/sell/seller/product/onsale?productId=${productInfo.productId}">上架</a></#if></td>

                            </tr>
                            </#list>

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
                                <a href="/sell/seller/product/list?page=${currentPage-1}&size=${size}">上一页</a>
                            </li>
                        </#if>
                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>

                        </#list>
                        <#if currentPage == productInfoPage.getTotalPages()>
                            <li class="disabled">
                                <a>下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/product/list?page=${currentPage+1}&size=${size}">下一页</a>
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