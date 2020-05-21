package gzq.upc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Deliverer {

    @Id
    private String idNum;

    private String deliveryId="";

    private Integer status=0;

    private String name;

    private String username;

    private String password;

    private String phone;

    private Integer orderTask;
}
