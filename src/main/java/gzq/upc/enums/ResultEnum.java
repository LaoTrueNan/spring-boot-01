package gzq.upc.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态异常"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"取消目标订单无商品"),
    PAY_STATUS_ERROR(17,"支付状态不正确"),
    PRODUCT_STATUS_ERROR(18,"商品状态不正确"),
    CATEGORY_DELETE_FAIL(19,"删除类目请将原有商品移出！"),
    DELIVERER_NOT_EXIST(20,"配送员不存在"),
    HFIRE_FAILED(21,"配送员操作失败"),
    FIRE_FAILED(22,"配送员有未完成订单"),
    ALREADY_EXIST(23,"类目名称重复"),
    TYPE_EXIST(24,"类目类型重复"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
