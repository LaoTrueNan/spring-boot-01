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
                                <th>类目id</th>
                                <th>类目名称</th>
                                <th>type</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list categoryList as productCategory>
                            <tr>
                                <td>${productCategory.categoryId}</td>
                                <td>${productCategory.categoryName}</td>
                                <td>${productCategory.categoryType}</td>
                                <td><a href="/seller/category/index?categoryId=${productCategory.categoryId}">修改</a></td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>


                </div>
            </div>
    </div>

</div>

</body>
</html>