package gzq.upc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gzq.upc.enums.ProductStatusEnum;
import gzq.upc.service.CategoryService;
import gzq.upc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private  Integer productStock;

    private String productIcon;

//    private String productDescription;

    private  Integer productStatus= ProductStatusEnum.DOWN.getCode();

    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus , ProductStatusEnum.class);
    }


}
