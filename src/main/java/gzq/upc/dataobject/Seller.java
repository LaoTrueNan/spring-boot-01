package gzq.upc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Seller {

    @Id
    private Integer id;

    private String name;

    private String address;

    private String avatar;

    private String bulletin;

    private BigDecimal deliveryPrice;

    private BigDecimal minPrice;

    private String workTime;

    private String deliveryTime;

    private String openTime;

}
