<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/seller/category/save" method="post">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" name="categoryName" value="${(category.categoryName)!''}" autocomplete="off"/>
                            </div>
                            <div class="form-group">
                                <label>type</label>
                                <input type="number" class="form-control" name="categoryType" value="${(category.categoryType)!''}" autocomplete="off"/>
                         </div>
                            <div class="form-group" hidden="hidden">
                                <label>type</label>
                            <input type="number" name="categoryId" value="${(category.categoryId)!''}" />

                            </div>
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>