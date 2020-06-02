package gzq.upc.repository;

import gzq.upc.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo , String>{
    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findByCategoryType(Integer categoryType);
    Page<ProductInfo> findBySupermarket(Integer supermarket, Pageable pageable);
}
