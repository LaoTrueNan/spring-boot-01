<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .gzqheader{
            margin: 35px;
        }
        .gzqheader h2{
            display: inline;
            margin-top: 0;
        }
        .gzqheader img{
            vertical-align: bottom;
        }
    </style>
    <script type="text/javascript" src="/jquery-3.5.0.min.js"></script>
    <script type="text/javascript" src="/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/order.js"></script>
</head>
<body>
<script type="text/javascript" src="/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#showDelivery").bind('click',function () {
            var orderId=$("#info").text();
            var url = "/delivery/detail?orderId="+orderId;
            var args = "";
            $.get(url,args,function (data) {
                alert(data);
            })
        })
    })
</script>
<div id="wrapper" class="toggled">
<div id="page-content-wrapper">
<div class="container">
    <div class="gzqheader"><img src="/img/list2.png" height="30px"><h2>订单详情</h2></div>
    <div class="row clearfix">
        <span id="info" hidden="hidden">${orderDTO.orderId}</span>
        <div class="col-md-4 column">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>
                        订单ID
                    </th>
                    <th>
                        订单总金额
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.orderAmount}
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>
                        商品ID
                    </th>
                    <th>
                        商品名称
                    </th>
                    <th>
                        单价
                    </th>
                    <th>
                        数量
                    </th>
                    <th>
                        总额
                    </th>

                </tr>
                </thead>
                <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <#if orderDTO.getOrderStatusEnum().message == "买家确认收货">
                <a href="/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
            </#if>
            <#if orderDTO.getOrderStatusEnum().message == "新订单"||orderDTO.getOrderStatusEnum().message == "等待配送">
                <a href="/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </#if>
            <#if orderDTO.getOrderStatusEnum().message == "配送中">
                <button id="showDelivery" type="button" class="btn btn-default btn-info">查看配送详情</button>
            </#if>
        </div>
    </div>
</div>
</div>
</div>
</body>
</html>