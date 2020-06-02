package gzq.upc.form;

import gzq.upc.enums.ProductStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private  Integer productStock;

    private String productIcon;

    private Integer supermarket;

//    private String productDescription;

    private  Integer productStatus= ProductStatusEnum.UP.getCode();

    private Integer categoryType;
}
