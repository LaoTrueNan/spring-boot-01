package gzq.upc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SellerInfo {

    private Integer id;

    @Id
    private String username;

    private String password;

    private String openid;

}
