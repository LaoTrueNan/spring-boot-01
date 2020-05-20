package gzq.upc.repository;

import gzq.upc.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String >{
    SellerInfo findByUsername(String username);

    Integer deleteByUsername(String username);
}
